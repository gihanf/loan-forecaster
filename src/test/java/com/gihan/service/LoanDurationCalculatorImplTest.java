package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.time.LocalDate.now;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import com.gihan.model.LoanDTO;

public class LoanDurationCalculatorImplTest {

    private static final BigDecimal LOAN_BALANCE = new BigDecimal(230_000);
    public static final String LOAN_DESCRIPTION = "loan description";
    public static final BigDecimal INTEREST_RATE = new BigDecimal(3.5);
    public static final BigDecimal PRINCIPAL_AMOUNT = new BigDecimal(300_000);
    private static final List<String> YEAR_LABELS_FOR_2017 = Arrays.asList(
            "Jan 17",
            "Feb 17",
            "Mar 17",
            "Apr 17",
            "May 17",
            "Jun 17",
            "Jul 17",
            "Aug 17",
            "Sep 17",
            "Oct 17",
            "Nov 17",
            "Dec 17"
    );
    private LoanDurationCalculatorImpl loanHelper;
    private LoanDTO loan;

    @Before
    public void setup() throws Exception {
        LocalDate elevenMonthsAgo = now().minusMonths(11);
        loanHelper = new LoanDurationCalculatorImpl();

        createLoan(elevenMonthsAgo, 1);
    }

    @Test
    public void shouldReturnRemainingLoanPeriod_Of1Month() throws Exception {
        createLoan(now().minusMonths(11), 1);

        Period remainingPeriod = loanHelper.getRemainingPeriod(loan);
        assertThat(remainingPeriod.getMonths(), is(1));
    }

    @Test
    public void shouldReturnRemainingLoanPeriod_OfZeroMonths() throws Exception {
        createLoan(now().minusMonths(12), 1);

        Period remainingPeriod = loanHelper.getRemainingPeriod(loan);
        assertThat(remainingPeriod.getMonths(), is(0));
    }

    @Test
    public void shouldReturnRemainingLoanPeriod_Of24Months() throws Exception {
        createLoan(now(), 2);

        Period remainingPeriod = loanHelper.getRemainingPeriod(loan);
        assertThat(remainingPeriod.getMonths(), is(24));
    }

    @Test
    public void shouldCalculateXAxisLabels_StartingFromJan16() throws Exception {
        createLoan(new LocalDate(2017, 1, 1), 1);

        Period remainingPeriod = loanHelper.getRemainingPeriod(loan);
        List<String> labels = loanHelper.getHorizontalAxisLabels(remainingPeriod);

        assertThat(labels.size() > 0, is(true));
        assertThat(labels.get(0), is("Jan 17"));
    }

    @Test
    public void shouldCalculateXAxisLabels_ForAnEntireYear() throws Exception {
        createLoan(new LocalDate(2017, 1, 1), 1);

        Period remainingPeriod = loanHelper.getRemainingPeriod(loan);
        List<String> labels = loanHelper.getHorizontalAxisLabels(remainingPeriod);

        assertThat(labels.size() > 0, is(true));
        assertThat(labels, is(YEAR_LABELS_FOR_2017));
    }

    private void createLoan(LocalDate startDate, int termInYears) {
        loan = new LoanDTO.LoanDTOBuilder()
                .withBalance(LOAN_BALANCE)
                .withDescription(LOAN_DESCRIPTION)
                .withId(1)
                .withInterestRate(INTEREST_RATE)
                .withPrincipalAmount(PRINCIPAL_AMOUNT)
                .withTerm(termInYears)
                .withStartDate(startDate)
                .build();
    }
}
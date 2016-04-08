package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;

public class PaymentDateCalculatorImplTest {

    private PaymentDateCalculator calculator;
    private final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat.forPattern("dd/mm/yyyy");

    @Before
    public void setup() throws Exception {
        calculator = new PaymentDateCalculatorImpl();
    }

    @Test
    public void shouldReturnOneDate_WhenFrequencyIsOnceOff_AndDateIsWithinRange() throws Exception {
        LocalDate expectedDate = LocalDate.now().plusMonths(1);
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, expectedDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(1));
        assertThat(localDates.get(0), is(expectedDate));
    }

    @Test
    public void shouldReturnEmptyList_WhenFrequencyIsOnceOff_AndDateIsNotInSearchRange() throws Exception {
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, LocalDate.now().minusMonths(1));
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(0));
    }

    @Test
    public void shouldReturn6Dates_WhenFrequencyIsMonthly_AndEntireScheduleFitsInSearchRange() throws Exception {
        LocalDate firstPaymentDate = DATETIME_FORMATTER.parseLocalDate("02/01/2016");
        LocalDate startDateRange = DATETIME_FORMATTER.parseLocalDate("01/01/2016");
//        LocalDate endDateRange = DATETIME_FORMATTER.parseLocalDate("01/02/2016");
        LocalDate endDateRange = LocalDate.parse("1/02/2016");
        System.out.println("startDateRange = " + startDateRange);
        System.out.println("endDateRange = " + endDateRange);

        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.MONTHLY, firstPaymentDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(startDateRange, endDateRange, paymentSchedule);

        assertThat(localDates.size(), is(1));
    }



}
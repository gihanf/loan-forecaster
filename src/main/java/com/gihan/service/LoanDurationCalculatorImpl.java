package com.gihan.service;


import static org.joda.time.LocalDate.now;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Period;

import com.gihan.model.LoanDTO;


public class LoanDurationCalculatorImpl implements LoanDurationCalculator {

    @Override
    public Period getRemainingPeriod(LoanDTO loan) {
        LocalDate startDate = loan.getStartDate();
        Period loanDuration = Period.years(loan.getTerm());
        LocalDate endDate = startDate.plus(loanDuration);

        Months monthsBetween = Months.monthsBetween(now(), endDate);
        return Period.months(monthsBetween.getMonths());
    }

    @Override
    public List<String> getHorizontalAxisLabels(Period remainingLoanDuration) {
        int numMonths = remainingLoanDuration.getMonths();

        IntStream remainingMonths = IntStream.range(0, numMonths + 1);
        return remainingMonths.mapToObj(month ->
        {
            LocalDate date = now();
            date = date.plusMonths(month);
            return date.toString("MMM yy");
        }).collect(Collectors.toList());
    }
}

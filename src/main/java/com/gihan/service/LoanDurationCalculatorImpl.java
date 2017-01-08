package com.gihan.service;



import static org.joda.time.LocalDate.now;

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
}

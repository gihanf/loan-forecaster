package com.gihan.service;

import java.util.List;

import org.joda.time.Period;

import com.gihan.model.LoanDTO;

public interface LoanDurationCalculator {

    Period getRemainingPeriod(LoanDTO loan);

    List<String> getHorizontalAxisLabels(Period remainingLoanDuration);
}

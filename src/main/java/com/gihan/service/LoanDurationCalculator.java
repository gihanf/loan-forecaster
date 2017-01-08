package com.gihan.service;

import org.joda.time.Period;

import com.gihan.model.LoanDTO;

public interface LoanDurationCalculator {

    Period getRemainingPeriod(LoanDTO loan);
}

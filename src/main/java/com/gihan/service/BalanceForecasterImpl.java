package com.gihan.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import com.gihan.model.Account;
import com.gihan.model.Payment;

public class BalanceForecasterImpl implements BalanceForecaster {

    private PaymentDateCalculator paymentDateCalculator;

    @Override
    public BigDecimal applyExpenses(Account account, List<Payment> payments, DateTime endDate) {
        return BigDecimal.ONE;
    }
}

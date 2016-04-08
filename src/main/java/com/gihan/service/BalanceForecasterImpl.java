package com.gihan.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import com.gihan.model.Account;
import com.gihan.model.Expense;

public class BalanceForecasterImpl implements BalanceForecaster {

    private PaymentDateCalculator paymentDateCalculator;

    @Override
    public BigDecimal applyExpenses(Account account, List<Expense> expenses, DateTime endDate) {
        return BigDecimal.ONE;
    }
}

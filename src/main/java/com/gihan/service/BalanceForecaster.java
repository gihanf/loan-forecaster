package com.gihan.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import com.gihan.model.Account;
import com.gihan.model.Payment;

public interface BalanceForecaster {

    public BigDecimal applyExpenses(Account account, List<Payment> payments, DateTime endDate);
}

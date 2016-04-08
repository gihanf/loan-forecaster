package com.gihan.model;

import java.math.BigDecimal;

public class Account {

    private String name;
    private BigDecimal balance;

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public void add(BigDecimal amount) {
        balance.add(amount);
    }

    public void subtract(BigDecimal amount) {
        balance.subtract(amount);
    }


    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}

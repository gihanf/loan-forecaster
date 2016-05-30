/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.gihan.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Expense implements Serializable{

    private int id;

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal amount;

//    @Type(type = )
    private PaymentSchedule paymentSchedule;

    public Expense() {}

    public Expense(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    public Expense(String description, BigDecimal amount, PaymentSchedule schedule) {
        this.description = description;
        this.amount = amount;
        this.paymentSchedule = schedule;
        this.paymentSchedule.setExpense(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
//                ", created=" + created + '\'' +
                ", amount=" + amount + '\'' +
//                ", expenseSchedule=" + expenseSchedule +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Expense)) {
            return false;
        }
        Expense expense = (Expense) obj;
        return expense.getDescription().equals(this.description)
                && expense.getAmount().equals(this.getAmount())
                && expense.getPaymentSchedule().equals(this.getPaymentSchedule());
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "expense")
    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }
}

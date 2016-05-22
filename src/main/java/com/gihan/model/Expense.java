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

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal amount;

    //    private Frequency frequency;

//    @Enumerated(EnumType.STRING)

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

//    private Calendar created = Calendar.getInstance();

//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private java.time.LocalDate newDate;
//
//    @OneToOne
//    private PaymentSchedule paymentSchedule;

    //    private LocalDate effectiveDate;
//    private PaymentScheduleOption expenseSchedule;

    public Expense() {}

    public Expense(String description, BigDecimal amount/*, PaymentSchedule paymentSchedule*/) {
        this.description = description;
        this.amount = amount;
//        this.paymentSchedule = paymentSchedule;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Calendar getCreated() {
//        return this.created;
//    }
//
//    public void setCreated(Calendar created) {
//        this.created = created;
//    }

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

//    public Frequency getFrequency() {
//        return frequency;
//    }
//
//    public void setFrequency(Frequency frequency) {
//        this.frequency = frequency;
//    }

//    public PaymentScheduleOption getExpenseSchedule() {
//        return expenseSchedule;
//    }
//
//    public void setExpenseSchedule(PaymentScheduleOption expenseSchedule) {
//        this.expenseSchedule = expenseSchedule;
//    }

//    public PaymentSchedule getPaymentSchedule() {
//        return paymentSchedule;
//    }
//
//    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
//        this.paymentSchedule = paymentSchedule;
//    }

//    public LocalDate getEffectiveDate() {
//        return effectiveDate;
//    }
//
//    public void setEffectiveDate(LocalDate effectiveDate) {
//        this.effectiveDate = effectiveDate;
//    }

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

//    public java.time.LocalDate getNewDate() {
//        return newDate;
//    }
//
//    public void setNewDate(java.time.LocalDate newDate) {
//        this.newDate = newDate;
//    }
}

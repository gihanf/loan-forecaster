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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Payment implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Description is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 9, fraction = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentDirection paymentDirection;

    private PaymentSchedule paymentSchedule;

    //TODO : Percentage of payment to apply / consider

    public Payment() {}

    public Payment(String description, BigDecimal amount, PaymentDirection paymentDirection, PaymentSchedule schedule) {
        this.description = description;
        this.amount = amount;
        this.paymentDirection = paymentDirection;
        this.paymentSchedule = schedule;
        this.paymentSchedule.setPayment(this);
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

    public PaymentDirection getPaymentDirection() {
        return paymentDirection;
    }

    public void setPaymentDirection(PaymentDirection paymentDirection) {
        this.paymentDirection = paymentDirection;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Payment)) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(obj, this, "id");
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }
}

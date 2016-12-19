package com.gihan.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

public class CandidateExpenseDTO {

    @NotEmpty(message = "Description must be entered")
    private String description;

    @Min(value = 0)
    @NotNull(message = "Amount must be entered")
    @Digits(message = "Amount should be numbers only. Up to two decimal places", integer = 9, fraction = 2)
    private BigDecimal amount;

    private Frequency frequency;

    private PaymentDirection paymentDirection;

    @NotNull (message = "First payment date must be entered")
    private LocalDate firstPaymentDate;

    public CandidateExpenseDTO() {
    }

    public CandidateExpenseDTO(String description, BigDecimal amount, Frequency frequency, LocalDate firstPaymentDate, PaymentDirection paymentDirection) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
        this.paymentDirection = paymentDirection;
    }

    public String getDescription() {
        return this.description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public PaymentDirection getPaymentDirection() {
        return paymentDirection;
    }

    public void setPaymentDirection(PaymentDirection paymentDirection) {
        this.paymentDirection = paymentDirection;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

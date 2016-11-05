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

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal amount;

    private Frequency frequency;

    private LocalDate firstPaymentDate;

    public CandidateExpenseDTO() {
    }

    public CandidateExpenseDTO(String description, BigDecimal amount, Frequency frequency) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
    }

    public CandidateExpenseDTO(String description, BigDecimal amount, Frequency frequency, LocalDate firstPaymentDate) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public CandidateExpenseDTO(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

package com.gihan.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

public class IncomeDTO {

    private int incomeId;

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 9, fraction = 2)
    private BigDecimal amount;

    private Frequency frequency;

    private LocalDate firstPaymentDate;

    public IncomeDTO() {
    }

    public IncomeDTO(int incomeId, String description, BigDecimal amount, Frequency frequency, LocalDate firstPaymentDate) {
        this.incomeId = incomeId;
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public int getExpenseId() {
        return incomeId;
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

    public void setExpenseId(int incomeId) {
        this.incomeId = incomeId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static class IncomeDTOBuilder {
        public int id;
        public String description;
        public BigDecimal amount;
        public Frequency frequency;
        public LocalDate firstPaymentDate;

        public IncomeDTOBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public IncomeDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public IncomeDTOBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public IncomeDTOBuilder withFrequency(Frequency frequency) {
            this.frequency = frequency;
            return this;
        }

        public IncomeDTOBuilder withFirstPaymentDate(LocalDate firstPaymentDate) {
            this.firstPaymentDate = firstPaymentDate;
            return this;
        }

        public IncomeDTO build() {
            return new IncomeDTO(
                    this.id,
                    this.description,
                    this.amount,
                    this.frequency,
                    this.firstPaymentDate
            );
        }
    }
}

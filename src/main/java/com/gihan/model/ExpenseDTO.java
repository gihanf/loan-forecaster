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

public class ExpenseDTO {

    private int expenseId;

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
//    @Pattern(regexp = "(0|[1-9]{1}\\d{0,8})(\\.{1}\\d{1,2}){0,1}")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal amount;

    private Frequency frequency;

    private LocalDate firstPaymentDate;

    public ExpenseDTO() {
    }

    public ExpenseDTO(int expenseId, String description, BigDecimal amount, Frequency frequency, LocalDate firstPaymentDate) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public int getExpenseId() {
        return expenseId;
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

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static class ExpenseDTOBuilder {
        public int id;
        public String description;
        public BigDecimal amount;
        public Frequency frequency;
        public LocalDate firstPaymentDate;

        public ExpenseDTOBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ExpenseDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseDTOBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseDTOBuilder withFrequency(Frequency frequency) {
            this.frequency = frequency;
            return this;
        }

        public ExpenseDTOBuilder withFirstPaymentDate(LocalDate firstPaymentDate) {
            this.firstPaymentDate = firstPaymentDate;
            return this;
        }

        public ExpenseDTO build() {
            return new ExpenseDTO(
                    this.id,
                    this.description,
                    this.amount,
                    this.frequency,
                    this.firstPaymentDate
            );
        }
    }
}

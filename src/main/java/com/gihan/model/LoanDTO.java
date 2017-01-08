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

public class LoanDTO {

    private int loanId;

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 9, fraction = 2)
    private BigDecimal principalAmount;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 9, fraction = 2)
    private BigDecimal currentBalance;

    private BigDecimal interestRate;

    private int term;

    private LocalDate startDate;

    public LoanDTO() {
    }

    public LoanDTO(int loanId, String description, BigDecimal principalAmount, BigDecimal currentBalance, BigDecimal interestRate, int term, LocalDate startDate){
        this.loanId = loanId;
        this.description = description;
        this.principalAmount = principalAmount;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
        this.term = term;
        this.startDate = startDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance= currentBalance;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public static class LoanDTOBuilder {
        public int id;
        public String description;
        public BigDecimal principalAmount;
        public BigDecimal balance;
        public BigDecimal interestRate;
        public int term;
        public LocalDate startDate;

        public LoanDTOBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public LoanDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public LoanDTOBuilder withPrincipalAmount(BigDecimal principalAmount) {
            this.principalAmount = principalAmount;
            return this;
        }

        public LoanDTOBuilder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public LoanDTOBuilder withInterestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public LoanDTOBuilder withTerm(int term) {
            this.term = term;
            return this;
        }

        public LoanDTOBuilder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public LoanDTO build() {
            return new LoanDTO(
                    this.id,
                    this.description,
                    this.principalAmount,
                    this.balance,
                    this.interestRate,
                    this.term,
                    this.startDate
            );
        }
    }
}

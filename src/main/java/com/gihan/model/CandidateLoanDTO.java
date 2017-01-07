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

public class CandidateLoanDTO {

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "principal amount should be numbers only", integer = 9, fraction = 2)
    private BigDecimal principalAmount;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "balance should be numbers only", integer = 9, fraction = 2)
    private BigDecimal balance;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "interest rate should be numbers only", integer = 3, fraction = 2)
    private BigDecimal interestRate;

    private int term;

    @NotNull (message = "First payment date must be entered")
    private LocalDate startDate;

    public CandidateLoanDTO() {
    }

    public CandidateLoanDTO(String description, BigDecimal principalAmount, BigDecimal balance, BigDecimal interestRate, int term, LocalDate startDate){
        this.description = description;
        this.principalAmount = principalAmount;
        this.balance = balance;
        this.interestRate = interestRate;
        this.term = term;
        this.startDate = startDate;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public BigDecimal getBalance() {
        return balance;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
}

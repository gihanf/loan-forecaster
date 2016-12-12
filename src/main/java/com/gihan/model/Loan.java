package com.gihan.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loanId;

    @NotEmpty(message = "Description is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "A principal amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal principalAmount;

    @Min(value = 0)
    @NotNull(message = "A balance must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal currentBalance;

    @Min(value = 0)
    @NotNull(message = "A rate must entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal interestRate;

    @NotNull(message = "A term must entered")
    private int term;

    public Loan() {}

    public Loan(String description, BigDecimal principalAmount, BigDecimal currentBalance, BigDecimal interestRate, int term) {
        this.description = description;
        this.interestRate = interestRate;
        this.principalAmount = principalAmount;
        this.currentBalance = currentBalance;
        this.term = term;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.loanId;
    }

    public void setId(int id) {
        this.loanId = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Loan)) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(obj, this, "id");
    }

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "expense")
//    public PaymentSchedule getPaymentSchedule() {
//        return paymentSchedule;
//    }
//
//    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
//        this.paymentSchedule = paymentSchedule;
//    }
}

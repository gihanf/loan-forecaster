package com.gihan.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.validation.annotation.Validated;

public class ExpenseDTO {

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

    public ExpenseDTO(String description, BigDecimal amount, Frequency frequency) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
    }

    public ExpenseDTO(String description, BigDecimal amount, Frequency frequency, LocalDate firstPaymentDate) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public ExpenseDTO(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate effectiveDate;

//    private Calendar created = Calendar.getInstance();

    //    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private java.time.LocalDate newDate;
//

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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Expense{" +
                ", description='" + description + '\'' +
//                ", created=" + created + '\'' +
                ", amount=" + amount + '\'' +
//                ", expenseSchedule=" + expenseSchedule +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }
}

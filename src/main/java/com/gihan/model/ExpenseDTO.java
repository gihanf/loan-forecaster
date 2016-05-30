package com.gihan.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExpenseDTO {

    @NotEmpty(message = "Summary is required.")
    private String description;

    @Min(value = 0)
    @NotNull(message = "An amount must be entered")
    @Digits(message = "Amount should be numbers only", integer = 3, fraction = 2)
    private BigDecimal amount;

    private Frequency frequency;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String description, BigDecimal amount, Frequency frequency) {
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
    }

    public ExpenseDTO(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }


    //    @Enumerated(EnumType.STRING)
//    private PaymentScheduleOption expenseSchedule;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate effectiveDate;

//    private Calendar created = Calendar.getInstance();

    //    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private java.time.LocalDate newDate;
//
//    private PaymentSchedule paymentSchedule;

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


}

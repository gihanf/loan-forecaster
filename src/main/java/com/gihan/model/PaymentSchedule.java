package com.gihan.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.joda.time.LocalDate;

@Entity
public class PaymentSchedule implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

//    @Column
//    private LocalDate firstPaymentDate;

    private Expense expense;

    public PaymentSchedule() {}

    public PaymentSchedule(Frequency frequency, LocalDate firstPaymentDate) {
        this.frequency = frequency;
//        this.firstPaymentDate = firstPaymentDate;
    }

    public PaymentSchedule(Frequency frequency) {
        this.frequency = frequency;
//        this.firstPaymentDate = LocalDate.now();
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

//    public LocalDate getFirstPaymentDate() {
//        return firstPaymentDate;
//    }
//
//    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
//        this.firstPaymentDate = firstPaymentDate;
//    }

    @Override
    public String toString() {
        return frequency.toString() + " "; /*+ firstPaymentDate.toString("dd-MM-yyyy");*/
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PaymentSchedule)) {
            return false;
        }
        PaymentSchedule paymentSchedule = (PaymentSchedule) other;
        return paymentSchedule.getFrequency() == this.getFrequency();
//                && paymentSchedule.getFirstPaymentDate() == this.getFirstPaymentDate();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result; //+ /*getFirstPaymentDate().hashCode()*/;
        return result;
    }

    @Id
    @OneToOne
    @JoinColumn(name = "expense_id")
    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}

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
public class PaymentSchedule implements UserType{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Column
    private LocalDate firstPaymentDate;

    public PaymentSchedule() {}

    public PaymentSchedule(Frequency frequency, LocalDate firstPaymentDate) {
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    @Override
    public String toString() {
        return frequency.toString() + " " + firstPaymentDate.toString("dd-MM-yyyy");
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PaymentSchedule)) {
            return false;
        }
        PaymentSchedule paymentSchedule = (PaymentSchedule) other;
        return paymentSchedule.getFrequency() == this.getFrequency()
                && paymentSchedule.getFirstPaymentDate() == this.getFirstPaymentDate();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getFirstPaymentDate().hashCode();
        return result;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
//        return new int[] {
//                BigDecimalType.INSTANCE.sqlType(),
//                CurrencyType.INSTANCE.sqlType(),
//        };
    }

    @Override
    public Class returnedClass() {
        return PaymentSchedule.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y)
            return true;
        if (null == x || null == y)
            return false;
        return x.equals(y);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        String frequency = resultSet.getString(strings[0]);
        String firstPaymentDate = resultSet.getString(strings[1]);
        if (frequency != null) {
            return new PaymentSchedule(Frequency.valueOf(frequency), LocalDate.parse(firstPaymentDate));
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (null == o) {
            preparedStatement.setNull(i, sqlTypes()[0]);
        } else {
            preparedStatement.setString(i, ((PaymentSchedule) o).toString());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}

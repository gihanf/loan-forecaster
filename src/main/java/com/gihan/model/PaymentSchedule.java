package com.gihan.model;


import javax.persistence.*;

import org.joda.time.LocalDate;

@Entity
public class PaymentSchedule /*implements UserType */{

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
//
//    @Override
//    public int[] sqlTypes() {
//        return new int[0];
//    }
//
//    @Override
//    public Class returnedClass() {
//        return null;
//    }
//
//    @Override
//    public boolean equals(Object o, Object o1) throws HibernateException {
//        return false;
//    }
//
//    @Override
//    public int hashCode(Object o) throws HibernateException {
//        return 0;
//    }
//
//    @Override
//    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
//        return null;
//    }
//
//    @Override
//    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
//
//    }
//
//    @Override
//    public Object deepCopy(Object o) throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public boolean isMutable() {
//        return false;
//    }
//
//    @Override
//    public Serializable disassemble(Object o) throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public Object assemble(Serializable serializable, Object o) throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
//        return null;
//    }
}

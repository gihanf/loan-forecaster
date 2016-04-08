package com.gihan.model;


import org.joda.time.LocalDate;

public class PaymentSchedule {

    private Frequency frequency;
    private LocalDate firstPaymentDate;

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

}

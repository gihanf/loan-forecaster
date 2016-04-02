package com.gihan.model;


import org.joda.time.LocalDateTime;

public class PaymentSchedule {

    private PaymentScheduleOption scheduleOption;
    private Frequency frequency;
    private LocalDateTime firstPaymentDate;

    public PaymentSchedule(PaymentScheduleOption scheduleOption, Frequency frequency, LocalDateTime firstPaymentDate) {
        this.scheduleOption = scheduleOption;
        this.frequency = frequency;
        this.firstPaymentDate = firstPaymentDate;
    }

    public PaymentScheduleOption getScheduleOption() {
        return scheduleOption;
    }

    public void setScheduleOption(PaymentScheduleOption scheduleOption) {
        this.scheduleOption = scheduleOption;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDateTime firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

}

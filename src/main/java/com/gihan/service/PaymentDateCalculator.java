package com.gihan.service;


import java.util.List;

import org.joda.time.LocalDate;

import com.gihan.model.PaymentSchedule;

public interface PaymentDateCalculator {

    List<LocalDate> calculatePaymentDates(LocalDate startDate, LocalDate endDate, PaymentSchedule schedule);

}

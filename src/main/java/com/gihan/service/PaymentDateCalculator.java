package com.gihan.service;


import java.util.List;

import org.joda.time.LocalDate;

public interface PaymentDateCalculator {

    List<LocalDate> calculatePaymentDates(LocalDate startDate, LocalDate endDate);

}

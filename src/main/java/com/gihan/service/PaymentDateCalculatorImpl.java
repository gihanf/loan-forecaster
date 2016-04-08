package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;

public class PaymentDateCalculatorImpl implements PaymentDateCalculator {

    @Override
    public List<LocalDate> calculatePaymentDates(LocalDate startDate, LocalDate endDate, PaymentSchedule paymentSchedule) {
        List<LocalDate> paymentDates = new ArrayList<LocalDate>();
        LocalDate firstPaymentDate = paymentSchedule.getFirstPaymentDate();

        if (paymentSchedule.getFrequency() == Frequency.ONCE_OFF) {
            if (firstPaymentDate.isAfter(startDate) && firstPaymentDate.isBefore(endDate)) {
                paymentDates.add(firstPaymentDate);
            }
        }
        if (paymentSchedule.getFrequency() == Frequency.MONTHLY) {
            if (firstPaymentDate.isAfter(startDate) && firstPaymentDate.isBefore(endDate)) {
                paymentDates.add(firstPaymentDate);
                LocalDate currentDate = firstPaymentDate;
                while (currentDate.isBefore(endDate)) {
                    currentDate = currentDate.plusMonths(1);
                    if (currentDate.isBefore(endDate)) {
                        paymentDates.add(currentDate);
                        System.out.println("date in range = " + currentDate);
                    }
                }
            }
        }
        return paymentDates;
    }
}

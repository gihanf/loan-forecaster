package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;

import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;

public class PaymentDateCalculatorImpl implements PaymentDateCalculator {

    private static final Log LOG = LogFactory.getLog(PaymentDateCalculatorImpl.class);

    @Override
    public List<LocalDate> calculatePaymentDates(LocalDate startDate, LocalDate endDate, PaymentSchedule paymentSchedule) {
        List<LocalDate> paymentDates = new ArrayList<LocalDate>();
        LocalDate firstPaymentDate = paymentSchedule.getFirstPaymentDate();

        if (paymentSchedule.getFrequency() == Frequency.ONCE_OFF) {
            if (firstPaymentDate.isAfter(startDate) && firstPaymentDate.isBefore(endDate)) {
                paymentDates.add(firstPaymentDate);
            }
            return paymentDates;
        }

        LocalDate currentDate = firstPaymentDate;
        while (currentDate.isBefore(endDate) || currentDate.compareTo(endDate) == 0) {
            if ((currentDate.isAfter(startDate) || currentDate.compareTo(startDate) == 0)
                    && (currentDate.isBefore(endDate) || currentDate.compareTo(endDate) == 0)) {
                paymentDates.add(firstPaymentDate);
                System.out.println("paymentDate = " + currentDate);
            }
            currentDate = getNextDateInSchedule(currentDate, paymentSchedule);
        }

        return paymentDates;
    }

    private LocalDate getNextDateInSchedule(LocalDate startDate, PaymentSchedule schedule) {
        LocalDate nextDateInSchedule = startDate;
        switch(schedule.getFrequency()) {
            case WEEKLY:
                nextDateInSchedule = startDate.plusWeeks(1);
                break;
            case MONTHLY:
                nextDateInSchedule = startDate.plusMonths(1);
                break;
            case YEARLY:
                nextDateInSchedule = startDate.plusYears(1);
                break;
            default:
                throw new RuntimeException("Unable to calculate next date, unhandled frequency: " + schedule.getFrequency());
        }
        return nextDateInSchedule;
    }
}

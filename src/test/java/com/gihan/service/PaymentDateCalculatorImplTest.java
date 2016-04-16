package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;

public class PaymentDateCalculatorImplTest {

    private PaymentDateCalculator calculator;

    @Before
    public void setup() throws Exception {
        calculator = new PaymentDateCalculatorImpl();
    }

    @Test
    public void shouldReturnOneDate_WhenFrequencyIsOnceOff_AndDateIsWithinRange() throws Exception {
        LocalDate expectedDate = LocalDate.now().plusMonths(1);
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, expectedDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(1));
        assertThat(localDates.get(0), is(expectedDate));
    }

    @Test
    public void shouldReturnDate_WhenFrequencyIsOnceOff_AndDateEqualsStartDateRange() throws Exception {
        LocalDate expectedDate = LocalDate.now();
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, expectedDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(1));
        assertThat(localDates.get(0), is(expectedDate));
    }

    @Test
    public void shouldReturnDate_WhenFrequencyIsOnceOff_AndDateEqualsEndDateRange() throws Exception {
        LocalDate expectedDate = LocalDate.now().plusMonths(6);
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, expectedDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(1));
        assertThat(localDates.get(0), is(expectedDate));
    }

    @Test
    public void shouldReturnEmptyList_WhenFrequencyIsOnceOff_AndDateIsNotInSearchRange() throws Exception {
        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.ONCE_OFF, LocalDate.now().minusMonths(1));
        List<LocalDate> localDates = calculator.calculatePaymentDates(LocalDate.now(), LocalDate.now().plusMonths(6), paymentSchedule);
        assertThat(localDates.size(), is(0));
    }

    @Test
    public void shouldReturnOneDate_WhenFrequencyIsMonthly_AndFirstPaymentFromSchedule_FitsInSearchRange() throws Exception {
        LocalDate firstPaymentDate = new LocalDate("2016-01-02");
        LocalDate rangeStartDate = new LocalDate("2016-01-01");
        LocalDate rangeEndDate = new LocalDate("2016-02-01");

        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.MONTHLY, firstPaymentDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(rangeStartDate, rangeEndDate, paymentSchedule);

        assertThat("Did not calculate correct number of payments within range", localDates.size(), is(1));
    }

    @Test
    public void shouldReturnTwoDates_WhenFrequencyIsMonthly_AndPaymentIsOnLastDayOfMonth() throws Exception {
        LocalDate firstPaymentDate = new LocalDate("2015-12-31");
        LocalDate rangeStartDate = new LocalDate("2016-01-01");
        LocalDate rangeEndDate = new LocalDate("2016-03-01");

        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.MONTHLY, firstPaymentDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(rangeStartDate, rangeEndDate, paymentSchedule);

        assertThat("Did not calculate correct number of payments within range", localDates.size(), is(2));
    }

    @Test
    public void shouldReturnFourDates_WhenFrequencyIs_Weekly_AndRangeIsAMonth() throws Exception {
        LocalDate firstPaymentDate = new LocalDate("2016-01-01");
        LocalDate rangeStartDate = new LocalDate("2016-01-01");
        LocalDate rangeEndDate = new LocalDate("2016-02-01");

        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.WEEKLY, firstPaymentDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(rangeStartDate, rangeEndDate, paymentSchedule);

        assertThat("Did not calculate correct number of payments within range", localDates.size(), is(5));
    }

    @Test
    public void shouldReturnTwoDates_WhenFrequencyIs_Yearly_AndPaymentDateLandsOn_EndDate_And_StartDate() throws Exception {
        LocalDate firstPaymentDate = new LocalDate("2016-01-01");
        LocalDate rangeStartDate = new LocalDate("2016-01-01");
        LocalDate rangeEndDate = new LocalDate("2017-01-01");

        PaymentSchedule paymentSchedule = new PaymentSchedule(Frequency.YEARLY, firstPaymentDate);
        List<LocalDate> localDates = calculator.calculatePaymentDates(rangeStartDate, rangeEndDate, paymentSchedule);

        assertThat("Did not calculate correct number of payments within range", localDates.size(), is(2));
    }

}
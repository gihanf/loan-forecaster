package com.gihan.service;

import com.gihan.application.LoanForecastApplication;
import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;
import com.gihan.repository.ExpenseRepository;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
@Transactional
public class ExpenseCreatorTest {

    @Autowired
    private ExpenseCreatorService expenseCreatorService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        expenseCreatorService.createExpense(
                new ExpenseDTO("description", new BigDecimal(24), Frequency.YEARLY));
        Expense expected = new Expense("description", new BigDecimal(24), new PaymentSchedule(Frequency.YEARLY));
        List<Expense> expenses = expenseRepository.findAll();
        assertThat(expenses.size(), is(1));
        Expense actualExpense = expenses.get(0);
        assertThat(actualExpense.equals(expected), is(true));
        assertThat(actualExpense.getAmount(), is(new BigDecimal(24)));
        assertThat(actualExpense.getDescription(), is("description"));
        assertThat(actualExpense.getPaymentSchedule().getFrequency(), is(Frequency.YEARLY));
    }

    @Test
    public void shouldCreateExpenseWith_FirstPaymentDate() {
        LocalDate expectedFirstPaymentDate = new LocalDate(2016, 2, 26);
        expenseCreatorService.createExpense(
                new ExpenseDTO("description", new BigDecimal(24), Frequency.YEARLY, expectedFirstPaymentDate));
        List<Expense> expenses = expenseRepository.findAll();
        assertThat(expenses.size(), is(1));
        Expense actualExpense = expenses.get(0);
        assertThat(actualExpense.getPaymentSchedule().getFirstPaymentDate(), is(expectedFirstPaymentDate));
    }
}
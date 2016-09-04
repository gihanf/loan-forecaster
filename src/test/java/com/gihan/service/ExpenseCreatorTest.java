package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.application.LoanForecastApplication;
import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.model.PaymentSchedule;
import com.gihan.repository.ExpenseRepository;

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
        expenseCreatorService.createExpense(new ExpenseDTO("description", new BigDecimal(2.12), Frequency.YEARLY));
        Expense firstExpense = new Expense("description", new BigDecimal(2.12), new PaymentSchedule(Frequency.YEARLY));

        List<Expense> expenses = expenseRepository.findAll();
        assertThat(expenses.size(), is(1));
        Expense actualExpense = expenses.get(0);
        assertThat(actualExpense, is(firstExpense));
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
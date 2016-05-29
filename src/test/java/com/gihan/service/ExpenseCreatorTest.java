package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

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
import com.gihan.repository.ExpenseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
//@Transactional
public class ExpenseCreatorTest {

    @Autowired
    private ExpenseCreatorService expenseCreatorService;

    @Autowired
    private ExpenseRepository expenseRepository;

//    @Autowired
//    private PaymentScheduleRepository paymentScheduleRepository;

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        expenseCreatorService.createExpense(
                new ExpenseDTO("description", new BigDecimal(24), Frequency.YEARLY));
        Expense expense = expenseRepository.findByDescription("description");
        assertThat(expense.getAmount(), is(new BigDecimal(24)));
        assertThat(expense.getPaymentSchedule(), notNullValue());
        assertThat(expense.getPaymentSchedule().getFrequency(), is(Frequency.YEARLY));
    }
}
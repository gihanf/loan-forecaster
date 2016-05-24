package com.gihan.service;

import com.gihan.application.LoanForecastApplication;
import com.gihan.model.Frequency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
public class ExpenseCreatorTest {

    @Autowired
    private ExpenseCreatorService expenseCreatorService;

//    @Autowired
//    private ExpenseRepository expenseRepository;

    @Before
    public void setUp() throws Exception {
//        expenseCreatorService = new ExpenseCreator();
    }

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        expenseCreatorService.createExpense(
                new ExpenseDTO("description", new BigDecimal(24), Frequency.YEARLY));

    }

    @Test
    public void shouldCreateExpense_WithCorrectPaymentScheduleInformation() throws Exception {

    }
}
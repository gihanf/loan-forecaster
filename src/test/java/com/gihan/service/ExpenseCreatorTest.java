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
import com.gihan.model.*;
import com.gihan.repository.ExpenseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
@Transactional
public class ExpenseCreatorTest {

    @Autowired
    private ExpenseService expenseCreatorService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        expenseCreatorService.createExpense(new CandidateExpenseDTO("description", new BigDecimal(2.12), Frequency.YEARLY));
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
                new CandidateExpenseDTO("description", new BigDecimal(24), Frequency.YEARLY, expectedFirstPaymentDate));
        List<Expense> expenses = expenseRepository.findAll();
        assertThat(expenses.size(), is(1));
        Expense actualExpense = expenses.get(0);
        assertThat(actualExpense.getPaymentSchedule().getFirstPaymentDate(), is(expectedFirstPaymentDate));
    }

    @Test
    public void shouldReturnAllExpenses() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        expenseCreatorService.createExpense(new CandidateExpenseDTO("description", new BigDecimal(2), Frequency.YEARLY, firstPaymentDate));
        List<ExpenseDTO> allExpenses = expenseCreatorService.getAllExpenses();
        assertThat("Incorrect number of expenses were retrieved from db, check db's original state", allExpenses.size(), is(1));
        assertThat(allExpenses.get(0).getAmount(), is(BigDecimal.valueOf(2)));
        assertThat(allExpenses.get(0).getDescription(), is("description"));
        assertThat(allExpenses.get(0).getFrequency(), is(Frequency.YEARLY));
        assertThat(allExpenses.get(0).getFirstPaymentDate(), is(firstPaymentDate));
    }

    /*@Test
    public void shouldConvertExpenseModelObjectsToExpenseDtos() throws Exception {


    }*/
}
package com.gihan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.application.LoanForecastApplication;
import com.gihan.model.*;
import com.gihan.repository.ExpenseRepository;
import com.gihan.testHelper.CandidateExpenseDtoBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
@Transactional
public class ExpenseCreatorTest {

    @Autowired
    private ExpenseService expenseCreatorService;

    @Autowired
    private ExpenseRepository expenseRepository;

    private CandidateExpenseDtoBuilder candidateExpenseDtoBuilder;

    @Before
    public void setup() throws Exception {
        candidateExpenseDtoBuilder = new CandidateExpenseDtoBuilder();
    }

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        BigDecimal expenseAmount = new BigDecimal(2);
        CandidateExpenseDTO dto = candidateExpenseDtoBuilder
                .withDescription("description")
                .withAmount(expenseAmount)
                .withFrequency(Frequency.YEARLY)
                .withFirstPaymentDate(firstPaymentDate)
                .build();

        expenseCreatorService.createExpense(dto);

        List<Expense> expenses = expenseRepository.findAll();
//        assertThat(expenses.size(), is(1));
        Expense createdExpense = expenses.get(0);
        assertThat("descriptions did not match", createdExpense.getDescription(), is("description"));
        assertThat("amounts did not match", createdExpense.getAmount(), is(expenseAmount));
        assertThat("first payment dates did not match", createdExpense.getPaymentSchedule().getFirstPaymentDate(), is(true));
        assertThat("payment frequencies did not match", createdExpense.getPaymentSchedule().getFrequency(), is(Frequency.YEARLY));
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
}
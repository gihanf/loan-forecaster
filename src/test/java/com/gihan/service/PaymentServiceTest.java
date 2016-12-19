package com.gihan.service;

import static com.gihan.model.PaymentDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class PaymentServiceTest {

    @Autowired
    private ExpenseService expenseCreatorService;

    @Autowired
    private ExpenseRepository expenseRepository;

    private CandidateExpenseDtoBuilder candidateExpenseDtoBuilder;
    private ExpenseDTO.ExpenseDTOBuilder expenseDtoBuilder;

    @Before
    public void setup() throws Exception {
        candidateExpenseDtoBuilder = new CandidateExpenseDtoBuilder();
        expenseDtoBuilder = new ExpenseDTO.ExpenseDTOBuilder();
    }

    @Test
    public void shouldCreateExpenseWith_AllExpenseFields() {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        BigDecimal expenseAmount = new BigDecimal(2.12).setScale(2, BigDecimal.ROUND_DOWN);
        CandidateExpenseDTO dto = candidateExpenseDtoBuilder
                .withDescription("description")
                .withAmount(expenseAmount)
                .withFrequency(Frequency.YEARLY)
                .withFirstPaymentDate(firstPaymentDate)
                .build();

        expenseCreatorService.createExpense(dto);

        List<Payment> payments = expenseRepository.findAll();
//        assertThat(expenses.size(), is(1));
        Payment createdPayment = payments.get(0);
        assertThat("descriptions did not match", createdPayment.getDescription(), is("description"));
        assertThat("amounts did not match", createdPayment.getAmount(), is(expenseAmount));
        assertThat("first payment dates did not match", createdPayment.getPaymentSchedule().getFirstPaymentDate(), is(firstPaymentDate));
        assertThat("payment frequencies did not match", createdPayment.getPaymentSchedule().getFrequency(), is(Frequency.YEARLY));
    }

    @Test
    public void shouldReturnAllExpenses() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        expenseCreatorService.createExpense(new CandidateExpenseDTO("description", new BigDecimal(2), Frequency.YEARLY, firstPaymentDate, OUTGOING));
        List<ExpenseDTO> allExpenses = expenseCreatorService.getAllExpenses();
        assertThat("Incorrect number of expenses were retrieved from db, check db's original state", allExpenses.size(), is(1));
        assertThat(allExpenses.get(0).getAmount(), is(BigDecimal.valueOf(2)));
        assertThat(allExpenses.get(0).getDescription(), is("description"));
        assertThat(allExpenses.get(0).getFrequency(), is(Frequency.YEARLY));
        assertThat(allExpenses.get(0).getFirstPaymentDate(), is(firstPaymentDate));
    }

    @Test
    public void shouldReturnExpensesOrderedDescendingByFirstPaymentDate() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        final String testDescription = "testDescription";
        CandidateExpenseDTO expenseToday = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate, OUTGOING);
        CandidateExpenseDTO expenseYesterday = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate.minusDays(1), OUTGOING);
        CandidateExpenseDTO expenseTomorrow = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate.plusDays(1), OUTGOING);
        expenseCreatorService.createExpense(expenseToday);
        expenseCreatorService.createExpense(expenseYesterday);
        expenseCreatorService.createExpense(expenseTomorrow);

        List<ExpenseDTO> testExpenses = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).collect(Collectors.toList());

        assertThat(testExpenses.size(), is(3));
        assertThat(testExpenses.get(0).getFirstPaymentDate(), is(firstPaymentDate.plusDays(1)));
        assertThat(testExpenses.get(1).getFirstPaymentDate(), is(firstPaymentDate));
        assertThat(testExpenses.get(2).getFirstPaymentDate(), is(firstPaymentDate.minusDays(1)));
    }

    @Test
    public void shouldBeAbleToEditAnExistingExpense() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        final String testDescription = "testDescription";
        CandidateExpenseDTO expenseToday = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate, OUTGOING);

        expenseCreatorService.createExpense(expenseToday);
        ExpenseDTO dto = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).findFirst().get();

        ExpenseDTO.ExpenseDTOBuilder builder = new ExpenseDTO.ExpenseDTOBuilder();
        ExpenseDTO modifiedDto = builder
                .withId(dto.getExpenseId())
                .withDescription("modifiedTestDescription")
                .withAmount(new BigDecimal(4))
                .withFrequency(Frequency.MONTHLY)
                .withFirstPaymentDate(firstPaymentDate.plusDays(1))
                .build();
        expenseCreatorService.modify(modifiedDto);

        Optional<ExpenseDTO> oldDto = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).findAny();
        Optional<ExpenseDTO> newDto = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals("modifiedTestDescription")).findAny();
        assertThat(oldDto.isPresent(), is(false));
        assertThat(newDto.isPresent(), is(true));

        ExpenseDTO savedDto = newDto.get();
        assertThat("The retrieved dto did not match what we intended to save", savedDto.equals(modifiedDto), is(true));
    }

    @Test
    public void shouldDeleteAnExpense() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        final String testDescription = "testDescription";
        CandidateExpenseDTO expenseToday = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate, OUTGOING);

        expenseCreatorService.createExpense(expenseToday);
        ExpenseDTO dto = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).findFirst().get();

        expenseCreatorService.delete(dto.getExpenseId());

        Optional<ExpenseDTO> deletedExpense = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).findFirst();

        assertThat(deletedExpense.isPresent(), is(false));
    }

    @Test
    public void shouldEditAnExpense() throws Exception {
        LocalDate firstPaymentDate = new LocalDate(2016, 2, 26);
        final String testDescription = "testDescription";
        CandidateExpenseDTO expenseToday = new CandidateExpenseDTO(testDescription, new BigDecimal(2), Frequency.YEARLY, firstPaymentDate, OUTGOING);

        expenseCreatorService.createExpense(expenseToday);

        ExpenseDTO originalDto = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals(testDescription)).findFirst().get();

        ExpenseDTO.ExpenseDTOBuilder expenseDTOBuilder = new ExpenseDTO.ExpenseDTOBuilder();

        ExpenseDTO modifiedDto = expenseDTOBuilder
                .withAmount(BigDecimal.TEN)
                .withDescription("modifiedTestDescription")
                .withFirstPaymentDate(firstPaymentDate.plusDays(1))
                .withFrequency(Frequency.WEEKLY)
                .withId(originalDto.getExpenseId())
                .build();
        expenseCreatorService.edit(modifiedDto);

        Optional<ExpenseDTO> editedExpense = expenseCreatorService.getAllExpenses().stream()
                .filter(expense -> expense.getDescription().equals("modifiedTestDescription")).findFirst();

        assertThat(editedExpense.isPresent(), is(true));
        ExpenseDTO dto = editedExpense.get();
        assertThat(dto.getExpenseId(), is(originalDto.getExpenseId()));
        assertThat(dto, is(modifiedDto));
    }
}
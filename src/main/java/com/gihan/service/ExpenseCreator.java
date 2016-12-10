package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.PaymentSchedule;
import com.gihan.repository.ExpenseRepository;

@Component
public class ExpenseCreator implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public long createExpense(CandidateExpenseDTO dto) {
        Expense expense = new Expense(dto.getDescription(), dto.getAmount(), new PaymentSchedule(dto.getFrequency(), dto.getFirstPaymentDate()));
        return expenseRepository.save(expense).getId();
    }

    @Override
    public void modify(ExpenseDTO modifiedDto) {
        Expense originalExpense = expenseRepository.findOne(modifiedDto.getExpenseId());
        originalExpense.setAmount(modifiedDto.getAmount());
        originalExpense.setDescription(modifiedDto.getDescription());

        PaymentSchedule paymentSchedule = originalExpense.getPaymentSchedule();
        paymentSchedule.setFirstPaymentDate(modifiedDto.getFirstPaymentDate());
        paymentSchedule.setFrequency(modifiedDto.getFrequency());

        expenseRepository.saveAndFlush(originalExpense);
    }

    @Override
    public void delete(int expenseId) {
        expenseRepository.delete(expenseId);
    }

    @Override
    public void edit(ExpenseDTO modifiedExpense) {

        Expense expense = expenseRepository.findById(modifiedExpense.getExpenseId());
        expense.setAmount(modifiedExpense.getAmount());
        expense.setDescription(modifiedExpense.getDescription());
        expense.getPaymentSchedule().setFirstPaymentDate(modifiedExpense.getFirstPaymentDate());
        expense.getPaymentSchedule().setFrequency(modifiedExpense.getFrequency());

        expenseRepository.save(expense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> exp = expenseRepository.findAll();
        final List<ExpenseDTO> expenses = new ArrayList<>();
        exp.stream()
                .sorted((a, b) -> a.getPaymentSchedule().getFirstPaymentDate().isBefore(b.getPaymentSchedule().getFirstPaymentDate()) ? 1 : -1)
                .forEach(e -> {
                    ExpenseDTO dto = new ExpenseDTO(
                            e.getId(),
                            e.getDescription(),
                            e.getAmount(),
                            e.getPaymentSchedule().getFrequency(),
                            e.getPaymentSchedule().getFirstPaymentDate()
                    );
                    expenses.add(dto);
                });
        return expenses;
    }
}

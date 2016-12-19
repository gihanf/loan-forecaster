package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.*;
import com.gihan.repository.ExpenseRepository;

@Component
public class ExpenseCreator implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public long createExpense(CandidateExpenseDTO dto) {
        Payment payment = new Payment(dto.getDescription(), dto.getAmount(), PaymentDirection.OUTGOING, new PaymentSchedule(dto.getFrequency(), dto.getFirstPaymentDate()));
        return expenseRepository.save(payment).getId();
    }

    @Override
    @Transactional
    public void modify(ExpenseDTO modifiedDto) {
        Payment originalPayment = expenseRepository.findOne(modifiedDto.getExpenseId());
        originalPayment.setAmount(modifiedDto.getAmount());
        originalPayment.setDescription(modifiedDto.getDescription());

        PaymentSchedule paymentSchedule = originalPayment.getPaymentSchedule();
        paymentSchedule.setFirstPaymentDate(modifiedDto.getFirstPaymentDate());
        paymentSchedule.setFrequency(modifiedDto.getFrequency());

        expenseRepository.saveAndFlush(originalPayment);
    }

    @Override
    public void delete(int expenseId) {
        expenseRepository.delete(expenseId);
    }

    @Override
    public void edit(ExpenseDTO modifiedExpense) {

        Payment payment = expenseRepository.findById(modifiedExpense.getExpenseId());
        payment.setAmount(modifiedExpense.getAmount());
        payment.setDescription(modifiedExpense.getDescription());
        payment.getPaymentSchedule().setFirstPaymentDate(modifiedExpense.getFirstPaymentDate());
        payment.getPaymentSchedule().setFrequency(modifiedExpense.getFrequency());

        expenseRepository.save(payment);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Payment> exp = expenseRepository.findAll();
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

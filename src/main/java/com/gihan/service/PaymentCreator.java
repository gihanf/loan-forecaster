package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.*;
import com.gihan.repository.PaymentRepository;

@Component
public class PaymentCreator implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public long createExpense(CandidateExpenseDTO dto) {
        Payment payment = new Payment(dto.getDescription(), dto.getAmount(), PaymentDirection.OUTGOING, new PaymentSchedule(dto.getFrequency(), dto.getFirstPaymentDate()));
        return paymentRepository.save(payment).getId();
    }

    @Override
    @Transactional
    public void modify(ExpenseDTO modifiedDto) {
        Payment originalPayment = paymentRepository.findOne(modifiedDto.getExpenseId());
        originalPayment.setAmount(modifiedDto.getAmount());
        originalPayment.setDescription(modifiedDto.getDescription());

        PaymentSchedule paymentSchedule = originalPayment.getPaymentSchedule();
        paymentSchedule.setFirstPaymentDate(modifiedDto.getFirstPaymentDate());
        paymentSchedule.setFrequency(modifiedDto.getFrequency());

        paymentRepository.saveAndFlush(originalPayment);
    }

    @Override
    public void delete(int expenseId) {
        paymentRepository.delete(expenseId);
    }

    @Override
    public void edit(ExpenseDTO modifiedExpense) {

        Payment payment = paymentRepository.findById(modifiedExpense.getExpenseId());
        payment.setAmount(modifiedExpense.getAmount());
        payment.setDescription(modifiedExpense.getDescription());
        payment.getPaymentSchedule().setFirstPaymentDate(modifiedExpense.getFirstPaymentDate());
        payment.getPaymentSchedule().setFrequency(modifiedExpense.getFrequency());

        paymentRepository.save(payment);
    }

    @Override
    public void edit(IncomeDTO modifiedIncome) {
        Payment payment = paymentRepository.findById(modifiedIncome.getIncomeId());
        payment.setAmount(modifiedIncome.getAmount());
        payment.setDescription(modifiedIncome.getDescription());
        payment.getPaymentSchedule().setFirstPaymentDate(modifiedIncome.getFirstPaymentDate());
        payment.getPaymentSchedule().setFrequency(modifiedIncome.getFrequency());

        paymentRepository.save(payment);
    }

    @Override
    public List<IncomeDTO> getAllIncomes() {
        List<Payment> exp = paymentRepository.findByPaymentDirection(PaymentDirection.INCOMING);
        final List<IncomeDTO> incomes = new ArrayList<>();
        exp.stream()
                .sorted((a, b) -> a.getPaymentSchedule().getFirstPaymentDate().isBefore(b.getPaymentSchedule().getFirstPaymentDate()) ? 1 : -1)
                .forEach(e -> {
                    IncomeDTO dto = new IncomeDTO(
                            e.getId(),
                            e.getDescription(),
                            e.getAmount(),
                            e.getPaymentSchedule().getFrequency(),
                            e.getPaymentSchedule().getFirstPaymentDate()
                    );
                    incomes.add(dto);
                });
        return incomes;
    }

    @Override
    public long createIncome(CandidateExpenseDTO dto) {
        Payment payment = new Payment(dto.getDescription(), dto.getAmount(), PaymentDirection.INCOMING, new PaymentSchedule(dto.getFrequency(), dto.getFirstPaymentDate()));
        return paymentRepository.save(payment).getId();
    }

    @Override
    public void deleteIncome(int incomeId) {
        paymentRepository.delete(incomeId);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Payment> exp = paymentRepository.findByPaymentDirection(PaymentDirection.OUTGOING);
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

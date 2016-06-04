package com.gihan.service;

import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.PaymentSchedule;
import com.gihan.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExpenseCreator implements ExpenseCreatorService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public long createExpense(ExpenseDTO dto) {
        Expense expense = new Expense(dto.getDescription(), dto.getAmount(), new PaymentSchedule(dto.getFrequency(), dto.getFirstPaymentDate()));
        return expenseRepository.save(expense).getId();
    }
}

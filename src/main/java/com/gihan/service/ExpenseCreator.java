package com.gihan.service;

import com.gihan.model.Expense;
import com.gihan.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCreator implements ExpenseCreatorService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public long createExpense(ExpenseDTO dto) {
        Expense expense = new Expense(dto.getDescription(), dto.getAmount()/*, dto.getPaymentSchedule()*/);
        return expenseRepository.save(expense).getId();
    }
}

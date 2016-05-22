package com.gihan.service;

import com.gihan.model.Expense;
import com.gihan.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseCreator implements ExpenseCreatorService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void createExpense(ExpenseDTO dto) {
        Expense expense = new Expense(dto.getDescription(), dto.getAmount()/*, dto.getPaymentSchedule()*/);
        messageRepository.save(expense);
    }
}

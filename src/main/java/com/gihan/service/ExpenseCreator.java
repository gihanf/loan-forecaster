package com.gihan.service;

import com.gihan.model.Expense;
import com.gihan.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCreator implements ExpenseCreatorService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public long createExpense(ExpenseDTO dto) {
        Expense expense = new Expense(dto.getDescription(), dto.getAmount()/*, dto.getPaymentSchedule()*/);
        return messageRepository.save(expense).getId();
    }
}

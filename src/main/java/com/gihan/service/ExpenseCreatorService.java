package com.gihan.service;

import java.util.List;

import com.gihan.model.ExpenseDTO;

public interface ExpenseCreatorService {

    long createExpense(ExpenseDTO dto);

    List<ExpenseDTO> getAllExpenses();
}

package com.gihan.service;

import com.gihan.model.ExpenseDTO;

public interface ExpenseCreatorService {

    long createExpense(ExpenseDTO dto);
}

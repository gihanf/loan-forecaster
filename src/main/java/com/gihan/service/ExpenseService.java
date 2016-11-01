package com.gihan.service;

import java.util.List;

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.ExpenseDTO;

public interface ExpenseService {

    long createExpense(CandidateExpenseDTO dto);

    List<ExpenseDTO> getAllExpenses();
}
package com.gihan.service;

import java.util.List;

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.ExpenseDTO;

public interface ExpenseService {

    long createExpense(CandidateExpenseDTO dto);

    List<ExpenseDTO> getAllExpenses();

    void modify(ExpenseDTO modifiedDto);

    void delete(int expenseId);

    void edit(ExpenseDTO expenseId);
}

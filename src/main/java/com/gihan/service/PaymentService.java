package com.gihan.service;

import java.util.List;

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.IncomeDTO;

public interface PaymentService {

    long createExpense(CandidateExpenseDTO dto);

    List<ExpenseDTO> getAllExpenses();

    void modify(ExpenseDTO modifiedDto);

    void delete(int expenseId);

    void edit(ExpenseDTO expenseId);

    List<IncomeDTO> getAllIncomes();
}

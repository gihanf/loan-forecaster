package com.gihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.Expense;

@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    Expense findByDescription(String description);

    Expense findById(int id);

//    @Query("UPDATE Expense expense set ")
//    void updateExpense(int expenseId, String description)

}

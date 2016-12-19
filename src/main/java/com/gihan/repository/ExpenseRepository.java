package com.gihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.Payment;

@Transactional
public interface ExpenseRepository extends JpaRepository<Payment, Integer> {

    Payment findByDescription(String description);

    Payment findById(int id);

}

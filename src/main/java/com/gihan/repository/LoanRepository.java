package com.gihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.Loan;

@Transactional
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Loan findById(int id);
}

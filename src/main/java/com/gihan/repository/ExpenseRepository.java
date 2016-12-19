package com.gihan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.Payment;
import com.gihan.model.PaymentDirection;

@Transactional
public interface ExpenseRepository extends JpaRepository<Payment, Integer> {

    Payment findByDescription(String description);

    Payment findById(int id);

    List<Payment> findByPaymentDirection(PaymentDirection paymentDirection);

}

package com.gihan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gihan.model.Expense;

@Transactional
public interface MessageRepository extends CrudRepository<Expense, Long> {

}

package com.gihan.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.gihan.model.Message;

@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByText(String lastName);
}

package com.gihan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gihan.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByText(String lastName);
}

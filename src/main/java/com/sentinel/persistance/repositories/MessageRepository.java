package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Integer> {


}

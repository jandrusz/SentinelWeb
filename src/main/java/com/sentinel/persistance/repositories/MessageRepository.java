package com.sentinel.persistance.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.Message;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Integer> {

	Message getMessageByIdChild(Integer idChild);

	@Override
	Message save(Message message);

}

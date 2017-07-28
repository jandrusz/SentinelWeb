package com.sentinel.message.service;

import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.Message;
import com.sentinel.persistance.repositories.MessageRepository;

@Component
public class MessageService {

	private MessageRepository messageRepository;

	@Inject
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Message getMessage(Integer idChild) {
		return messageRepository.getMessageByIdChild(idChild);
	}

	public boolean saveMessage(Message message) {
		return Objects.nonNull(messageRepository.save(message));
	}

}

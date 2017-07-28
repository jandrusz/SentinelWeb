package com.sentinel.message.service;

import com.sentinel.persistance.domain.Message;
import com.sentinel.persistance.repositories.MessageRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Objects;

@Component
public class MessageService {

    private MessageRepository messageRepository;

    @Inject
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message getMessage(Integer idChild){
        return messageRepository.getMessageByIdChild(idChild);
    }

    public boolean saveMessage(Message message){
        return Objects.nonNull(messageRepository.save(message));
    }

}

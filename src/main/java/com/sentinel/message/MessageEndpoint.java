package com.sentinel.message;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Message;

@RestController
@RequestMapping(value = "message")
public class MessageEndpoint {

	private MessageService messageService;

	@Inject
	public MessageEndpoint(MessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(value = "saveMessage", method = RequestMethod.POST)
	public boolean saveMessage(Message message) {
		return messageService.saveMessage(message);
	}

	@RequestMapping(value = "getMessage", method = RequestMethod.GET)
	public Message getMessage(Child child) {
		return messageService.getMessage(child.getIdChild());
	}

}

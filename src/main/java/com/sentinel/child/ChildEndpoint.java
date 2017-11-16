package com.sentinel.child;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.persistance.domain.Child;

@RestController
@RequestMapping(value = "child")
public class ChildEndpoint {

	private ChildService childService;

	@Inject
	public ChildEndpoint(ChildService childService) {
		this.childService = childService;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Child login(Child child) {
		return childService.getChildByLoginAndPassword(child);
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Child register(Child child) {
		return childService.saveChild(child);
	}
}

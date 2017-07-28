package com.sentinel.child.endpoint;

import com.sentinel.child.service.ChildService;
import com.sentinel.persistance.domain.Child;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

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
    public boolean register(Child child) {
        return childService.saveChild(child);
    }
}

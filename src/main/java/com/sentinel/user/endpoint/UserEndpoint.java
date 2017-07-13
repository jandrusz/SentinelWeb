package com.sentinel.user.endpoint;

import com.sentinel.persistance.domain.User;
import com.sentinel.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class UserEndpoint {

    private UserService userService;

    @Inject
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public User logIn(User user) {
        return userService.getUser(user);
    }

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

}

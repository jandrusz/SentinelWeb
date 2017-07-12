package com.sentinel.api;

import com.sentinel.persistance.domain.User;
import com.sentinel.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class UserApi {

    private UserService userService;

    @Inject
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public List<User> logIn(@RequestParam("login") String login) {
        return userService.getUsers(login);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public User saveUser(@RequestParam("login") String login) {
        return userService.saveUser(login);
    }

}

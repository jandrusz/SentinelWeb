package com.sentinel.start.user;

import com.sentinel.hibernate.UserDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUser {

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("login") String login, @RequestParam("password") String password) {
        return UserDAO.getUserData(login, password);
    }
}
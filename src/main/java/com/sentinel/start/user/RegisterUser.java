package com.sentinel.start.user;

import com.sentinel.hibernate.dao.UserDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUser {

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login, @RequestParam("password") String password) {
        return UserDAO.addUser(firstName, lastName, login, password);
    }

}

package com.sentinel.start;

import com.sentinel.hibernate.UserDTO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        return UserDTO.createJsonResponseFromRequest(email, password);
    }
}
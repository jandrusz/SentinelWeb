package com.sentinel.register;

import com.sentinel.hibernate.UserDTO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email, @RequestParam("password") String password) {
        return UserDTO.addUser(0, firstName, lastName, email, password);
    }

}

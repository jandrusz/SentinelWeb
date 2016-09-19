package com.sentinel.login;

import com.sentinel.hibernate.UserDTO;
import com.sentinel.persistance.User;
import org.hibernate.HibernateException;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

@RestController
public class Login {

    @Produces("application/json")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        return UserDTO.createJsonResponseFromRequest(email, password);
    }
}
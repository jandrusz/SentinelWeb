package com.sentinel.login;

import com.sentinel.hibernate.UserDTO;
import com.sentinel.persistance.User;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class Login {

    @RequestMapping(value = "/{email}/{password}", method = RequestMethod.POST)
    public JSONObject logIn(@PathVariable("email") String email, @PathVariable("password") String password) {
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        User user = UserDTO.getUser(email, password);

        obj2.put("id", user.id.trim());
        obj2.put("firstName", user.firstName.trim());
        obj2.put("lastName", user.lastName.trim());
        obj2.put("email", user.email.trim());
        obj2.put("password", user.password.trim());
        obj.put("success", obj2);
        return obj;
    }
}
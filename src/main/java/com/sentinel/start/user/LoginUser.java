package com.sentinel.start.user;

import com.sentinel.hibernate.UserDAO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUser {

    @Value("${spring.datasource.username}")
    String s;

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("login") String login, @RequestParam("password") String password) {
            JSONObject jsonObject = new JSONObject();


        jsonObject.put("cos",s);
return jsonObject;
//        return UserDAO.getUserData(login, password);
    }
}
package com.sentinel.start.child;

import com.sentinel.hibernate.dao.ChildDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginChild {

    @RequestMapping(value = "/loginChild", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("login") String login, @RequestParam("password") String password) {
        return ChildDAO.getChildData(login, password);
    }
}
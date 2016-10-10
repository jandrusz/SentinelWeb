package com.sentinel.start.child;


import com.sentinel.hibernate.ChildDAO;
import com.sentinel.hibernate.UserDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterChild {

    @RequestMapping(value = "/registerChild", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login, @RequestParam("password") String password) {
        return ChildDAO.addChild(0, firstName, lastName, login, password);
    }

}

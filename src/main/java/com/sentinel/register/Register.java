package com.sentinel.register;

import com.sentinel.hibernate.UserDTO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {

    @RequestMapping("/register")
    public JSONObject register() {
        JSONObject obj = new JSONObject();
        return obj;
    }

}

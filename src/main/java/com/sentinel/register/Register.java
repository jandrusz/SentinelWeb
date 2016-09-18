package com.sentinel.register;

import com.sentinel.hibernate.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam("email") String email, @RequestParam("password") String password,
                         @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        UserDTO.addUser(0, firstName, lastName, email, password);
    }

}

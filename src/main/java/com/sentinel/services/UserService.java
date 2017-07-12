package com.sentinel.services;


import com.google.common.collect.Lists;
import com.sentinel.persistance.domain.User;
//import com.sentinel.persistance.repositories.UserDao;
import com.sentinel.persistance.repositories.UserDao;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class UserService {

    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers(String login) {
        return userDao.findUsersByLogin(login);
    }

    public User saveUser(String login) {
        User user = new User();
        user.setId(0);
        user.setLogin(login);
        user.setFirstName(login);
        user.setLastName(login);
        user.setPassword(login);
       return userDao.save(user);
//        return userDao.save(User.builder()
//                .id(0)
//                .login(login)
//                .build());
    }

}

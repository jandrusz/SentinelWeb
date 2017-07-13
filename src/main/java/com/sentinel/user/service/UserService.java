package com.sentinel.user.service;


import com.sentinel.persistance.domain.User;
import com.sentinel.persistance.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Objects;

@Component
public class UserService {

    private UserRepository userDao;

    @Inject
    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public User getUser(User user) {
        return userDao.findByLoginAndPassword(user.getLogin(), user.getPassword());
    }

    public User saveUser(User user) {
        if (Objects.nonNull(user.getLogin()) && Objects.nonNull(user.getPassword())) {
            return userDao.save(user);
        }
        return new User();
    }

}

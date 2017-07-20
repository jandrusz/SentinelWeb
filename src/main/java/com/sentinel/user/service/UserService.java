package com.sentinel.user.service;


import com.sentinel.persistance.domain.User;
import com.sentinel.persistance.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@Component
public class UserService {

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLoginAndPassword(User user) {
        if (validateCredentials(user)) {
            return userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        }
        return null;
    }

    public User getUserByIdUser(Integer idUser) {
        return userRepository.getUserByIdUser(idUser);
    }

    @Transactional
    public boolean saveUser(User user) {
        if (Objects.isNull(getUserByLoginAndPassword(user))) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean validateCredentials(User user) {
        return Objects.nonNull(user.getLogin()) && Objects.nonNull(user.getPassword());
    }

}
package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    public List<User> findUsersByLogin(String login);

}
package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByLoginAndPassword(String login, String password);

    User getUserByIdUser(Integer idUser);

    @Override
    User save(User user);

}
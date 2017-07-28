package com.sentinel.persistance.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User getUserByLoginAndPassword(String login, String password);

	User getUserByIdUser(Integer idUser);

	@Override
	User save(User user);

}
package com.sentinel.user.service;

import java.util.Objects;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.User;
import com.sentinel.persistance.repositories.UserRepository;

@Component
public class UserService {

	private UserRepository userRepository;

	@Inject
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserByLoginAndPassword(User user) {
		return validateCredentials(user) ? userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword()) : null;
	}

	public User getUserByIdUser(Integer idUser) {
		return userRepository.getUserByIdUser(idUser);
	}

	@Transactional
	public User saveUser(User user) {
		return Objects.isNull(getUserByLoginAndPassword(user)) ? userRepository.save(user) : null;
	}

	private boolean validateCredentials(User user) {
		return Objects.nonNull(user.getLogin()) && Objects.nonNull(user.getPassword());
	}

}

package com.sentinel.user.service

import com.sentinel.persistance.domain.User
import com.sentinel.persistance.repositories.UserRepository
import spock.lang.Specification

class UserServiceTest extends Specification {

    UserRepository userRepository = Mock(UserRepository)
    UserService userService

    def setup() {
        userService = new UserService(userRepository)
    }

    def "should successfully validate credentials"() {
        when:
        User user = new User(login: Fields.LOGIN, password: Fields.PASSWORD)

        then:
        userService.validateCredentials(user)
    }

    def "should check that password is missing"() {
        when:
        User user = new User(login: Fields.LOGIN)

        then:
        !userService.validateCredentials(user)
    }

    def "should call repository to getUserByLoginAndPassword"() {
        given:
        User user = new User(login: Fields.LOGIN, password: Fields.PASSWORD)

        when:
        userService.getUserByLoginAndPassword(user)

        then:
        1 * userRepository.getUserByLoginAndPassword(_, _) >> new User()
    }

    def "should not call repository to getUserByLoginAndPassword if password is missing"() {
        given:
        User user = new User(login: Fields.LOGIN)

        when:
        userService.getUserByLoginAndPassword(user)

        then:
        0 * userRepository.getUserByLoginAndPassword(_, _) >> new User()
    }

    def "should not call repository to getUserByLoginAndPassword if login is missing"() {
        given:
        User user = new User(password: Fields.PASSWORD)

        when:
        userService.getUserByLoginAndPassword(user)

        then:
        0 * userRepository.getUserByLoginAndPassword(_, _) >> new User()
    }

    class Fields {
        private static LOGIN = "login"
        private static PASSWORD = "password"
    }

}

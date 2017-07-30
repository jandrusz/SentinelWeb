package com.sentinel.child.service

import com.sentinel.persistance.domain.Child
import com.sentinel.persistance.repositories.ChildRepository
import spock.lang.Specification

class ChildServiceTest extends Specification {

    ChildRepository childRepository
    ChildService childService

    def setup() {
        childRepository = Mock(ChildRepository)
        childService = new ChildService(childRepository)
    }

    def "should successfully validate credentials"() {
        when:
        Child child = new Child(login: Fields.LOGIN, password: Fields.PASSWORD)

        then:
        childService.validateCredentials(child)
    }

    def "should check that password is missing"() {
        when:
        Child child = new Child(login: Fields.LOGIN)

        then:
        !childService.validateCredentials(child)
    }

    def "should call repository to getChildByLoginAndPassword"() {
        given:
        Child child = new Child(login: Fields.LOGIN, password: Fields.PASSWORD)

        when:
        childService.getChildByLoginAndPassword(child)

        then:
        1 * childRepository.getChildByLoginAndPassword(_, _) >> new Child()
    }

    def "should not call repository to getChildByLoginAndPassword if password is missing"() {
        given:
        Child child = new Child(login: Fields.LOGIN)

        when:
        childService.getChildByLoginAndPassword(child)

        then:
        0 * childRepository.getChildByLoginAndPassword(_, _) >> new Child()
    }

    def "should not call repository to getChildByLoginAndPassword if login is missing"() {
        given:
        Child child = new Child(password: Fields.PASSWORD)

        when:
        childService.getChildByLoginAndPassword(child)

        then:
        0 * childRepository.getChildByLoginAndPassword(_, _) >> new Child()
    }

    def "should get child by id"() {
        when:
        Child child = childService.getChildByIdChild(0)

        then:
        1 * childRepository.getChildByIdChild(_) >> new Child()
        child != null
    }

    def "should save child"() {
        given:
        Child child = new Child(login: Fields.LOGIN, password: Fields.PASSWORD)

        when:
        childService.saveChild(child)

        then:
        1 * childRepository.save(_) >> new Child()
    }

    def "should not save child"() {
        given:
        Child child = new Child(login: Fields.LOGIN, password: Fields.PASSWORD)
        childRepository.getChildByLoginAndPassword(_, _) >> new Child()

        when:
        childService.saveChild(child)

        then:
        0 * childRepository.save(_)
    }

    def "should set schedule for child id"() {
        when:
        boolean scheduleSet = childService.setSchedule(1, 1)

        then:
        1 * childRepository.setSchedule(_, _) >> 1
        scheduleSet
    }

    def "should not set schedule for child id"() {
        when:
        boolean scheduleSet = childService.setSchedule(1, 1)

        then:
        1 * childRepository.setSchedule(_, _) >> 0
        !scheduleSet
    }

    class Fields {
        private static LOGIN = "login"
        private static PASSWORD = "password"
    }

}

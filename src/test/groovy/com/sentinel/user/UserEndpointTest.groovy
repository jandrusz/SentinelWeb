package com.sentinel.user

import com.google.common.collect.Lists
import com.sentinel.monitor.MonitorService
import com.sentinel.persistance.domain.Child
import com.sentinel.persistance.domain.Monitor
import com.sentinel.persistance.domain.User
import spock.lang.Specification

class UserEndpointTest extends Specification {

    UserService userService
    MonitorService monitorService
    UserEndpoint userEndpoint

    def setup() {
        userService = Mock(UserService)
        monitorService = Mock(MonitorService)
        userEndpoint = new UserEndpoint(userService, monitorService)
    }

    def "should login user"() {
        when:
        userEndpoint.login(new User())

        then:
        1 * userService.getUserByLoginAndPassword(_) >> new User()
    }

    def "should register user"() {
        when:
        userEndpoint.register(new User())

        then:
        1 * userService.saveUser(_) >> new User()
    }

    def "should return list of children for user"() {
        when:
        List<Child> children = userEndpoint.getChildren(new User())

        then: "should call monitorService to get Monitor objects"
        1 * monitorService.getUserChildrenUnderObservation(_) >> Lists.newArrayList(new Monitor(), new Monitor())

        and: "should return list with 2 children"
        children.size() == 2
    }

    def "should add bind between user and child"() {
        when:
        userEndpoint.addChildToObservation(new User(), new Child())

        then:
        1 * monitorService.addChildToObservation(_, _) >> []
    }

    def "should delete bind with child"() {
        when:
        boolean removed = userEndpoint.removeChildFromObservation(new User(), new Child())

        then:
        1 * monitorService.deleteMonitorByIdUserAndIdChild(_, _) >> true
        removed
    }
}

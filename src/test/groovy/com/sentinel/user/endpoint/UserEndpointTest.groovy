package com.sentinel.user.endpoint

import com.google.common.collect.Lists
import com.sentinel.child.service.ChildService
import com.sentinel.monitor.service.MonitorService
import com.sentinel.persistance.domain.Child
import com.sentinel.persistance.domain.Monitor
import com.sentinel.persistance.domain.User
import com.sentinel.user.service.UserService
import spock.lang.Specification


class UserEndpointTest extends Specification {

    UserService userService
    ChildService childService
    MonitorService monitorService
    UserEndpoint userEndpoint

    def setup() {
        userService = Mock(UserService)
        childService = Mock(ChildService)
        monitorService = Mock(MonitorService)
        userEndpoint = new UserEndpoint(userService, childService, monitorService)
    }

    def "should login user"() {
        when:
        User user = userEndpoint.login(new User())

        then:
        1 * userService.getUserByLoginAndPassword(_) >> new User()
        user != null
    }

    def "should register user"() {
        when:
        User user = userEndpoint.register(new User())

        then:
        1 * userService.saveUser(_) >> new User()
        user != null
    }

    def "should return list of children for user"() {
        when:
        List<Child> children = userEndpoint.getChildren(new User())

        then: "should call monitorService to get Monitor objects"
        1 * monitorService.getMonitorsOfUsersChildren(_) >> Lists.newArrayList(new Monitor(), new Monitor())

        then: "should call childService to get children by ids"
        2 * childService.getChildByIdChild(_)

        then: "should return list with 2 children"
        children.size() == 2
    }

    def "should add bind between user and child"() {
        when:
        Monitor monitor = userEndpoint.addChild(new User(), new Child())

        then:
        1 * childService.getChildByLoginAndPassword(_) >> new Child()
        1 * monitorService.save(_) >> new Monitor()
        monitor != null
    }

    def "should delete bind with child"() {
        when:
        boolean removed = userEndpoint.removeChildFromObservation(new User(), new Child())

        then:
        1 * monitorService.deleteMonitorByIdUserAndIdChild(_, _) >> true
        removed
    }
}

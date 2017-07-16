package com.sentinel.child.endpoint

import com.sentinel.child.service.ChildService
import com.sentinel.persistance.domain.Child
import spock.lang.Specification

class ChildEndpointTest extends Specification {

    ChildService childService
    ChildEndpoint childEndpoint

    def setup() {
        childService = Mock(ChildService)
        childEndpoint = new ChildEndpoint(childService)
    }

    def "should login user"() {
        when:
        Child child = childEndpoint.login(new Child())

        then:
        1 * childService.getChildByLoginAndPassword(_) >> new Child()
        child != null
    }

    def "should register user"() {
        when:
        boolean registered = childEndpoint.register(new Child())

        then:
        1 * childService.saveChild(_) >> true
        registered
    }


}

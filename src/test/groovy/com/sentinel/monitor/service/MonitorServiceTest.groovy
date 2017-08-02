package com.sentinel.monitor.service

import com.sentinel.child.service.ChildService
import com.sentinel.persistance.domain.Child
import com.sentinel.persistance.domain.Monitor
import com.sentinel.persistance.domain.User
import com.sentinel.persistance.repositories.MonitorRepository
import spock.lang.Specification

class MonitorServiceTest extends Specification {

    MonitorRepository monitorRepository
    ChildService childService
    MonitorService monitorService

    def setup() {
        monitorRepository = Mock(MonitorRepository)
        childService = Mock(ChildService)
        monitorService = new MonitorService(monitorRepository, childService)
    }

    def "should return children that user observes"() {
        when:
        List<Child> children = monitorService.getUserChildrenUnderObservation(Fields.ID_USER)

        then:
        1 * monitorRepository.getMonitorsByIdUser(_) >> [new Monitor(), new Monitor()]
        2 * childService.getChildByIdChild(_) >> new Child()
        children.size() == 2
    }

    def "should not return children if user does not observed any"() {
        when:
        List<Child> children = monitorService.getUserChildrenUnderObservation(Fields.ID_USER)

        then:
        1 * monitorRepository.getMonitorsByIdUser(_) >> []
        0 * childService.getChildByIdChild(_) >> new Child()
        children.size() == 0
    }

    def "should add child to observation"() {
        when:
        Monitor monitor = monitorService.addChildToObservation(new User(idUser: Fields.ID_USER), new Child(idChild: Fields.ID_CHILD))

        then:
        1 * childService.getChildByLoginAndPassword(_) >> new Child(idChild: Fields.ID_CHILD)
        1 * monitorRepository.save(_) >> new Monitor()
        monitor
    }

    def "should delete monitor if it succeeded in database"(){
        when:
        boolean deleted = monitorService.deleteMonitorByIdUserAndIdChild(Fields.ID_USER, Fields.ID_CHILD)

        then:
        1 * monitorRepository.deleteMonitorByIdUserAndIdChild(_, _) >> 1
        deleted
    }

    def "should not delete monitor if it failed in database"(){
        when:
        boolean deleted = monitorService.deleteMonitorByIdUserAndIdChild(Fields.ID_USER, Fields.ID_CHILD)

        then:
        1 * monitorRepository.deleteMonitorByIdUserAndIdChild(_, _) >> 0
        !deleted
    }

    class Fields {
        static final ID_USER = 1
        static final ID_CHILD = 1
    }

}

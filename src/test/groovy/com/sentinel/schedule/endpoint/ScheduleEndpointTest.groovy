package com.sentinel.schedule.endpoint

import com.sentinel.child.service.ChildService
import com.sentinel.persistance.domain.Schedule
import com.sentinel.persistance.domain.User
import com.sentinel.schedule.service.ScheduleService
import spock.lang.Specification

class ScheduleEndpointTest extends Specification {

    ScheduleService scheduleService
    ChildService childService
    ScheduleEndpoint scheduleEndpoint

    def setup() {
        scheduleService = Mock(ScheduleService)
        childService = Mock(ChildService)
        scheduleEndpoint = new ScheduleEndpoint(scheduleService, childService)
    }

    def "should add schedule"() {
        when:
        scheduleEndpoint.addSchedule(new Schedule())

        then:
        1 * scheduleService.saveSchedule(_)
    }

    def "should get schedules for user"() {
        when:
        List<Schedule> schedules = scheduleEndpoint.getSchedules(new User())

        then:
        1 * scheduleService.getSchedulesByIdUser(_) >> [new Schedule(), new Schedule()]
        schedules.size() == 2
    }

    def "should remove schedule"() {
        when:
        scheduleEndpoint.remove(new Schedule())

        then:
        1 * scheduleService.removeScheduleByIdSchedule(_)
    }

    //TODO add tests
}

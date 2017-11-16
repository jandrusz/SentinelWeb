package com.sentinel.schedule

import com.sentinel.child.ChildService
import com.sentinel.persistance.domain.Child
import com.sentinel.persistance.domain.Schedule
import com.sentinel.persistance.domain.User
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

    def "should return schedule if it was successfully added"() {
        when:
        Schedule schedule = scheduleEndpoint.addSchedule(new Schedule())

        then:
        1 * scheduleService.saveSchedule(_) >> new Schedule()
        schedule
    }

    def "should not return schedule if it failed to add"() {
        when:
        Schedule schedule = scheduleEndpoint.addSchedule(new Schedule())

        then:
        1 * scheduleService.saveSchedule(_) >> null
        !schedule
    }

    def "should get schedules for user"() {
        when:
        List<Schedule> schedules = scheduleEndpoint.getSchedules(new User())

        then:
        1 * scheduleService.getSchedulesByIdUser(_) >> [new Schedule(), new Schedule()]
        schedules.size() == 2
    }

    def "should not return schedules for user"() {
        when:
        List<Schedule> schedules = scheduleEndpoint.getSchedules(new User())

        then:
        1 * scheduleService.getSchedulesByIdUser(_) >> []
        schedules.size() == 0
    }

    def "should return true if schedule was successfully removed"() {
        when:
        boolean removed = scheduleEndpoint.remove(new Schedule())

        then:
        1 * scheduleService.removeScheduleByIdSchedule(_) >> true
        removed
    }

    def "should return false if schedule failed to remove"() {
        when:
        boolean removed = scheduleEndpoint.remove(new Schedule())

        then:
        1 * scheduleService.removeScheduleByIdSchedule(_) >> false
        !removed
    }

    def "should return true if successfully edited schedule name"() {
        when:
        boolean edited = scheduleEndpoint.editScheduleName(new Schedule())

        then:
        1 * scheduleService.editName(_) >> true
        edited
    }

    def "should return false if editing failed"() {
        when:
        boolean edited = scheduleEndpoint.editScheduleName(new Schedule())

        then:
        1 * scheduleService.editName(_) >> false
        !edited
    }

    def "should return schedule"() {
        when:
        Schedule schedule = scheduleEndpoint.getSchedule(new Child())

        then:
        1 * scheduleService.getScheduleByIdSchedule(_) >> new Schedule()
        schedule
    }

    def "should not return schedule"() {
        when:
        Schedule schedule = scheduleEndpoint.getSchedule(new Child())

        then:
        1 * scheduleService.getScheduleByIdSchedule(_) >> null
        !schedule
    }

    def "should return true if successfully set schedule for child"() {
        when:
        boolean scheduleSet = scheduleEndpoint.bindChildToSchedule(new Child(), new Schedule())

        then:
        childService.setSchedule(_, _) >> true
        scheduleSet
    }

    def "should return false if unsuccessfully set schedule for child"() {
        when:
        boolean scheduleSet = scheduleEndpoint.bindChildToSchedule(new Child(), new Schedule())

        then:
        childService.setSchedule(_, _) >> false
        !scheduleSet
    }

}

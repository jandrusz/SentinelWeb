package com.sentinel.schedule.service

import com.sentinel.persistance.domain.Schedule
import com.sentinel.persistance.repositories.ScheduleRepository
import spock.lang.Specification

class ScheduleServiceTest extends Specification {

    ScheduleRepository scheduleRepository
    ScheduleService scheduleService

    def setup() {
        scheduleRepository = Mock(ScheduleRepository)
        scheduleService = new ScheduleService(scheduleRepository)
    }

    def "should save schedule"() {
        when:
        scheduleService.saveSchedule(new Schedule())

        then:
        1 * scheduleRepository.save(_)
    }

    def "should get schedules by user id"() {
        when:
        List<Schedule> schedules = scheduleService.getSchedulesByIdUser(1)

        then:
        1 * scheduleRepository.getSchedulesByIdUser(_) >> [new Schedule(), new Schedule()]
        schedules.size() == 2
    }

    def "should remove schedule by schedule id"() {
        when:
        boolean removed = scheduleService.removeScheduleByIdSchedule(1)

        then:
        1 * scheduleRepository.removeScheduleByIdSchedule(_) >> 1
        removed
    }

    def "should not remove schedule by schedule id"() {
        when:
        boolean removed = scheduleService.removeScheduleByIdSchedule(1)

        then:
        1 * scheduleRepository.removeScheduleByIdSchedule(_) >> 0
        !removed
    }

    def "should edit name od schedule"() {
        when:
        boolean edited = scheduleService.editName(new Schedule())

        then:
        1 * scheduleRepository.editName(_, _) >> 1
        edited
    }

    def "should not edit name od schedule"() {
        when:
        boolean edited = scheduleService.editName(new Schedule())

        then:
        1 * scheduleRepository.editName(_, _) >> 0
        !edited
    }

    def "should get schedule by schedule id"() {
        when:
        scheduleService.getScheduleByIdSchedule(1)

        then:
        1 * scheduleRepository.getScheduleByIdSchedule(_)
    }

}

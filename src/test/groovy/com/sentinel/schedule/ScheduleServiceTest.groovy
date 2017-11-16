package com.sentinel.schedule

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
        List<Schedule> schedules = scheduleService.getSchedulesByIdUser(Fields.ID_USER)

        then:
        1 * scheduleRepository.getSchedulesByIdUser(_) >> [new Schedule(), new Schedule()]
        schedules.size() == 2
    }

    def "should remove schedule by schedule id"() {
        when:
        boolean removed = scheduleService.removeScheduleByIdSchedule(Fields.ID_USER)

        then:
        1 * scheduleRepository.removeScheduleByIdSchedule(_) >> Fields.SUCCESS_INTEGER
        removed
    }

    def "should not remove schedule by schedule id"() {
        when:
        boolean removed = scheduleService.removeScheduleByIdSchedule(Fields.ID_USER)

        then:
        1 * scheduleRepository.removeScheduleByIdSchedule(_) >> Fields.FAIL_INTEGER
        !removed
    }

    def "should edit name of schedule"() {
        when:
        boolean edited = scheduleService.editName(new Schedule())

        then:
        1 * scheduleRepository.editName(_, _) >> Fields.SUCCESS_INTEGER
        edited
    }

    def "should not edit name of schedule"() {
        when:
        boolean edited = scheduleService.editName(new Schedule())

        then:
        1 * scheduleRepository.editName(_, _) >> Fields.FAIL_INTEGER
        !edited
    }

    def "should get schedule by schedule id"() {
        when:
        scheduleService.getScheduleByIdSchedule(Fields.ID_USER)

        then:
        1 * scheduleRepository.getScheduleByIdSchedule(_)
    }


    class Fields {
        static final ID_USER = 1
        static final SUCCESS_INTEGER = 1
        static final FAIL_INTEGER = 0
    }

}

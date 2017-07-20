package com.sentinel.schedule.endpoint;


import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.domain.User;
import com.sentinel.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(value = "schedule")
public class ScheduleEndpoint {

    ScheduleService scheduleService;

    @Inject
    public ScheduleEndpoint(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "addSchedule", method = RequestMethod.POST)
    public Schedule addSchedule(Schedule schedule) {
        return scheduleService.saveSchedule(schedule);
    }

//        @RequestMapping(value = "scheduleEntries", method = RequestMethod.POST)
//    public JSONObject getSchedule(@RequestParam("idSchedule") String idSchedule) {
//        return ScheduleEntryDAO.getScheduleEntriesByScheduleId(idSchedule);
//    }

    @RequestMapping(value = "schedules", method = RequestMethod.POST)
    public List<Schedule> getSchedules(User user) {
        return scheduleService.getSchedulesByIdUser(user.getIdUser());
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public boolean remove(Schedule schedule) {
        return scheduleService.removeScheduleByIdSchedule(schedule.getIdSchedule());
    }

    @RequestMapping(value = "editName", method = RequestMethod.POST)
    public boolean editScheduleName(Schedule schedule) {
        return scheduleService.editName(schedule);
    }

//    @RequestMapping(value = "bindChildToSchedule", method = RequestMethod.POST)
//    public JSONObject bindChildToSchedule(@RequestParam("idChild") String idChild, @RequestParam("idSchedule") String idSchedule) {
//        return ChildDAO.bindChildToSchedule(idChild, idSchedule);
//    }
//
//    @RequestMapping(value = "addScheduleEntry", method = RequestMethod.POST)
//    public JSONObject addOrEditScheduleEntry(@RequestParam("name") String name, @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop,
//                                             @RequestParam("day") String day, @RequestParam("idSchedule") String idSchedule, @RequestParam("idScheduleEntry") String idScheduleEntry,
//                                             @RequestParam("idArea") String idArea) {
//        return ScheduleEntryDAO.addOrEditScheduleEntry(name, timeStart, timeStop, day, idSchedule, idScheduleEntry, idArea);
//    }
//
//    @RequestMapping(value = "removeScheduleEntry", method = RequestMethod.POST)
//    public JSONObject removeScheduleEntry(@RequestParam("scheduleEntryId") String scheduleEntryId) {
//        return ScheduleEntryDAO.removeScheduleEntry(scheduleEntryId);
//    }

}

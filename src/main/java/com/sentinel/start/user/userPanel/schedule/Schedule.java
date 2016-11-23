package com.sentinel.start.user.userPanel.schedule;

import com.sentinel.hibernate.dao.ChildDAO;
import com.sentinel.hibernate.dao.ScheduleDAO;
import com.sentinel.hibernate.dao.ScheduleEntryDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Schedule {

    @RequestMapping(value = "/schedule/addScheduleEntry", method = RequestMethod.POST)
    public JSONObject addOrEditScheduleEntry(@RequestParam("name") String name, @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop,
                                             @RequestParam("day") String day, @RequestParam("idSchedule") String idSchedule, @RequestParam("idScheduleEntry") String idScheduleEntry,
                                             @RequestParam("idArea") String idArea) {
        return ScheduleEntryDAO.addOrEditScheduleEntry(name, timeStart, timeStop, day, idSchedule, idScheduleEntry, idArea);
    }

    @RequestMapping(value = "/schedule/removeScheduleEntry", method = RequestMethod.POST)
    public JSONObject removeScheduleEntry(@RequestParam("scheduleEntryId") String scheduleEntryId) {
        return ScheduleEntryDAO.removeScheduleEntry(scheduleEntryId);
    }

    @RequestMapping(value = "/schedule/addSchedule", method = RequestMethod.POST)
    public JSONObject addSchedule(@RequestParam("name") String name, @RequestParam("idUser") String idUser) {
        return ScheduleDAO.addSchedule(name, idUser);
    }

    @RequestMapping(value = "/schedule/removeSchedule", method = RequestMethod.POST)
    public JSONObject removeSchedule(@RequestParam("idSchedule") String idSchedule) {
        return ScheduleDAO.removeSchedule(idSchedule);
    }

    @RequestMapping(value = "/schedule/editScheduleName", method = RequestMethod.POST)
    public JSONObject editScheduleName(@RequestParam("idSchedule") String idSchedule, @RequestParam("newName") String newName) {
        return ScheduleDAO.editScheduleName(idSchedule, newName);
    }

    @RequestMapping(value = "/schedule/bindChildToSchedule", method = RequestMethod.POST)
    public JSONObject bindChildToSchedule(@RequestParam("idChild") String idChild, @RequestParam("idSchedule") String idSchedule) {
        return ChildDAO.bindChildToSchedule(idChild, idSchedule);
    }

}

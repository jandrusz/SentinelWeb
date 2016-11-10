package com.sentinel.start.user.userPanel.schedule;

import com.sentinel.hibernate.ScheduleDAO;
import com.sentinel.hibernate.ScheduleEntryDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Schedule {

    @RequestMapping(value = "/schedule/addScheduleEntry", method = RequestMethod.POST)
    public JSONObject addScheduleEntry(@RequestParam("name") String name, @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop,
                                       @RequestParam("day") String day, @RequestParam("idSchedule") String idSchedule) {
        return ScheduleEntryDAO.addScheduleEntry(name, timeStart, timeStop, day, idSchedule);
    }

    @RequestMapping(value = "/schedule/addSchedule", method = RequestMethod.POST)
    public JSONObject addSchedule(@RequestParam("name") String name, @RequestParam("idUser") String idUser) {
        return ScheduleDAO.addSchedule(0, name, idUser);

    }

    @RequestMapping(value = "/schedule/removeSchedule", method = RequestMethod.POST)
    public JSONObject removeSchedule(@RequestParam("idSchedule") String idSchedule) {
        return ScheduleDAO.removeSchedule(idSchedule);
    }

    @RequestMapping(value = "/schedule/editScheduleName", method = RequestMethod.POST)
    public JSONObject editScheduleName(@RequestParam("idSchedule") String idSchedule, @RequestParam("newName") String newName) {
        return ScheduleDAO.editScheduleName(idSchedule, newName);
    }

}

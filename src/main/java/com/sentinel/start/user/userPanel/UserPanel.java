package com.sentinel.start.user.userPanel;

import com.sentinel.hibernate.ChildDAO;
import com.sentinel.hibernate.MonitorDAO;
import com.sentinel.hibernate.ScheduleDAO;
import com.sentinel.hibernate.ScheduleEntryDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserPanel {

    @RequestMapping(value = "/schedules/scheduleEntries", method = RequestMethod.POST)
    public JSONObject getSchedule(@RequestParam("idSchedule") String idSchedule) {
        return ScheduleEntryDAO.getScheduleEntriesByScheduleId(idSchedule);
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public JSONObject getSchedules(@RequestParam("idUser") String idUser ) {
        return ScheduleDAO.getSchedulesByUserId(idUser);
    }

    @RequestMapping(value = "/children", method = RequestMethod.POST)
    public JSONObject getChildren(@RequestParam("idUser") String idUser) {
        return ChildDAO.getUserChildrenByUserId(idUser);
    }

    //TODO dodanie sprawdzania czy dany rodzic jest juz sparowany z dzieckiem
    @RequestMapping(value = "/children/addChild", method = RequestMethod.POST)
    public JSONObject addChild(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("idUser") String idUser) {
        return MonitorDAO.bindChildToParent(login, password, idUser);
    }

    @RequestMapping(value = "/children/removeChild", method = RequestMethod.POST)
    public JSONObject removeChild(@RequestParam("idUser") String idUser, @RequestParam("idChild") String idChild) {
        return MonitorDAO.unbindUserFromChild(idUser, idChild);
    }

}

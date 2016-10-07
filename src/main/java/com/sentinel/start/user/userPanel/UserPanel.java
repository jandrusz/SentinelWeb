package com.sentinel.start.user.userPanel;

import com.sentinel.hibernate.ChildDAO;
import com.sentinel.hibernate.MonitorDAO;
import com.sentinel.hibernate.ScheduleDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserPanel {

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public JSONObject getSchedule(@RequestParam("idChild") String idChild) {
        return ScheduleDAO.getScheduleByChildId(idChild);
    }

    @RequestMapping(value = "/children", method = RequestMethod.POST)
    public JSONObject getChildren(@RequestParam("idUser") String idUser) {
        return ChildDAO.getUserChildrenByUserId(idUser);
    }

    @RequestMapping(value = "/children/addChild", method = RequestMethod.POST)
    public JSONObject addChild(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("login") String login, @RequestParam("idUser") String idUser) {
        return ChildDAO.bindUserToChild(0, firstName, lastName, login, idUser);
    }

    @RequestMapping(value = "/children/removeChild", method = RequestMethod.POST)
    public JSONObject removeChild(@RequestParam("idUser") String idUser, @RequestParam("idChild") String idChild) {
        return MonitorDAO.unbindUserFromChild(idUser, idChild);
    }

}

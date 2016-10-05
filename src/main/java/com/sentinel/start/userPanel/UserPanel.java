package com.sentinel.start.userPanel;

import com.sentinel.hibernate.ChildDTO;
import com.sentinel.hibernate.MonitorDTO;
import com.sentinel.hibernate.ScheduleDTO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserPanel {

    @RequestMapping(value = "/children", method = RequestMethod.POST)
    public JSONObject getChildren(@RequestParam("id") String id) {
        return ChildDTO.getChildrenByUserEmail(id);
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public JSONObject getSchedule(@RequestParam("id") String id) {
        return ScheduleDTO.getSchedule(id);
    }

    @RequestMapping(value = "/children/addChild", method = RequestMethod.POST)
    public JSONObject addChild(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("id") String id) {
        return ChildDTO.addChild(0, firstName, lastName, email, id);
    }

}

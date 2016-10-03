package com.sentinel.start.userPanel;

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
    public JSONObject getChildren(@RequestParam("email") String email) {
        return MonitorDTO.getChildrenByUserEmail(email);
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public JSONObject getSchedule(@RequestParam("idChild") String idChild, @RequestParam("idUser") String idUser) {
        return ScheduleDTO.getSchedule(idChild,idUser);
    }


}

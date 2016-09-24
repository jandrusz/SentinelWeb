package com.sentinel.login.userPanel;


import com.sentinel.hibernate.MonitorDTO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
public class UserPanel {

    @Produces("application/json")
    @RequestMapping(value = "/children", method = RequestMethod.POST)
    public JSONObject getChildren(@RequestParam("email") String email) {
        return MonitorDTO.getChildrenByUserEmail(email);
    }

    @Produces("application/json")
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public JSONObject getSchedule(@RequestParam("idChild") String idChild, @RequestParam("idUser") String idUser) {
        return new JSONObject();
    }


}

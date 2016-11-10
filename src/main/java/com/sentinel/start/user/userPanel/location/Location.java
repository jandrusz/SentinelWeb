package com.sentinel.start.user.userPanel.location;

import com.sentinel.hibernate.LocationDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Location {


    @RequestMapping(value = "location/getLocation", method = RequestMethod.POST)
    public JSONObject getLocation(@RequestParam("idChild") String idChild) {

        return LocationDAO.getLocation(idChild);
    }


}

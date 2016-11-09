package com.sentinel.start.child.location;

import com.sentinel.hibernate.LocationDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Location {

    @RequestMapping(value = "location/saveLocation", method = RequestMethod.POST)
    public JSONObject saveLocation(@RequestParam("longitude") String longitude,
                                   @RequestParam("latitude") String latitude, @RequestParam("day") String day,
                                   @RequestParam("time") String time) {
        return LocationDAO.saveLocation(0, longitude, latitude, day, time);
    }

}

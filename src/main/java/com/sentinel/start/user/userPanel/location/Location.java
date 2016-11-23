package com.sentinel.start.user.userPanel.location;

import com.sentinel.hibernate.dao.AreaDAO;
import com.sentinel.hibernate.dao.LocationDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Location {

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public JSONObject getLocation(@RequestParam("idChild") String idChild) {
        return LocationDAO.getLocations(idChild);
    }

    @RequestMapping(value = "/locations/checkChildPosition", method = RequestMethod.POST)
    public JSONObject checkLocation(@RequestParam("idChild") String idChild) {
        return LocationDAO.checkLocation(idChild);
    }

    @RequestMapping(value = "/area/addArea", method = RequestMethod.POST)
    public JSONObject saveArea(@RequestParam("areaLatitude") String areaLatitude,
                               @RequestParam("areaLongitude") String areaLongitude, @RequestParam("areaRadius") String areaRadius) {
        return AreaDAO.saveArea(areaLatitude, areaLongitude, areaRadius);
    }

}

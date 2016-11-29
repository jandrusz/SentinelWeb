package com.sentinel.api;

import com.sentinel.hibernate.dao.ChildDAO;
import com.sentinel.hibernate.dao.LocationDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildApi {

    @RequestMapping(value = "/loginChild", method = RequestMethod.POST)
    public JSONObject logIn(@RequestParam("login") String login, @RequestParam("password") String password) {
        return ChildDAO.getChildData(login, password);
    }

    @RequestMapping(value = "/registerChild", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login, @RequestParam("password") String password) {
        return ChildDAO.addChild(firstName, lastName, login, password);
    }

    @RequestMapping(value = "location/saveLocation", method = RequestMethod.POST)
    public JSONObject saveLocation(@RequestParam("longitude") String longitude,
                                   @RequestParam("latitude") String latitude, @RequestParam("day") String day,
                                   @RequestParam("time") String time, @RequestParam("idChild") String idChild) {
        return LocationDAO.saveLocation(longitude, latitude, day, time, idChild);
    }

}

package com.sentinel.child.endpoint;

import com.sentinel.child.service.ChildService;
import com.sentinel.persistance.domain.Child;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "child")
public class ChildEndpoint {

    private ChildService childService;

    @Inject
    public ChildEndpoint(ChildService childService) {
        this.childService = childService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Child logIn(Child child) {
        return childService.getChildByLoginAndPassword(child);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public boolean register(Child child) {
        return childService.saveChild(child);
    }
//
//    @RequestMapping(value = "location/saveLocation", method = RequestMethod.POST)
//    public JSONObject saveLocation(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("day") String day,
//                                   @RequestParam("time") String time, @RequestParam("idChild") String idChild) {
//        return LocationDAO.saveLocation(longitude, latitude, day, time, idChild);
//    }
//
//    @RequestMapping(value = "/schedules/schedule", method = RequestMethod.POST)
//    public JSONObject getSchedules(@RequestParam("idChild") String idChild) {
//        return ScheduleDAO.getScheduleByIdForChild(idChild);
//    }
//
//    @RequestMapping(value = "/message", method = RequestMethod.POST)
//    public JSONObject getMessage(@RequestParam("idChild") String idChild) {
//        return MessageDAO.getMessage(idChild);
//    }

}

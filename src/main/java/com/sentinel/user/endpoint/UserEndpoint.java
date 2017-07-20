package com.sentinel.user.endpoint;

import com.sentinel.child.service.ChildService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Monitor;
import com.sentinel.persistance.domain.User;
import com.sentinel.monitor.service.MonitorService;
import com.sentinel.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "user")
public class UserEndpoint {

    private UserService userService;

    private ChildService childService;

    private MonitorService monitorService;

    @Inject
    public UserEndpoint(UserService userService, ChildService childService, MonitorService monitorService) {
        this.userService = userService;
        this.childService = childService;
        this.monitorService = monitorService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public User login(User user) {
        return userService.getUserByLoginAndPassword(user);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public boolean register(User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "children", method = RequestMethod.GET)
    public List<Child> getChildren(User user) {
        List<Monitor> monitors = monitorService.getMonitorsOfUsersChildren(user.getIdUser());
        return monitors.stream()
                .map(m -> childService.getChildByIdChild(m.getIdChild()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "addChild", method = RequestMethod.POST)
    public Monitor addChild(User user, Child child) {
        Monitor monitor = Monitor.builder()
                .idUser(user.getIdUser())
                .idChild(childService.getChildByLoginAndPassword(child).getIdChild())
                .build();
        return monitorService.save(monitor);
    }

    @RequestMapping(value = "removeChild", method = RequestMethod.POST)
    public boolean removeChild(User user, Child child) {
        return monitorService.deleteMonitorByIdUserAndIdChild(user.getIdUser(), child.getIdChild());
    }

//
//    @RequestMapping(value = "/locations", method = RequestMethod.POST)
//    public JSONObject getLocation(@RequestParam("idChild") String idChild) {
//        return LocationDAO.getLocations(idChild);
//    }
//
//    @RequestMapping(value = "/filteredLocations", method = RequestMethod.POST)
//    public JSONObject getFilteredLocations(@RequestParam("idChild") String idChild, @RequestParam("dateStart") String dateStart, @RequestParam("dateStop") String dateStop,
//                                           @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop) {
//        return LocationDAO.getLocations(idChild, dateStart, dateStop, timeStart, timeStop);
//    }
//
//    @RequestMapping(value = "/locations/checkChildPosition", method = RequestMethod.POST)
//    public JSONObject checkLocation(@RequestParam("idChild") String idChild) {
//        return LocationDAO.checkLocation(idChild);
//    }
//
//    @RequestMapping(value = "/area/addArea", method = RequestMethod.POST)
//    public JSONObject saveArea(@RequestParam("areaLatitude") String areaLatitude, @RequestParam("areaLongitude") String areaLongitude,
//                               @RequestParam("areaRadius") String areaRadius) {
//        return AreaDAO.saveArea(areaLatitude, areaLongitude, areaRadius);
//    }
//
//    @RequestMapping(value = "/area/getArea", method = RequestMethod.POST)
//    public JSONObject getAreaForScheduleEntry(@RequestParam("idArea") String idArea) {
//        return AreaDAO.getAreaForScheduleEntry(idArea);
//    }
//
//    @RequestMapping(value = "/messageReceiver", method = RequestMethod.POST)
//    public JSONObject saveMessage(@RequestParam("idChild") String idChild, @RequestParam("idUser") String idUser, @RequestParam("message") String message,@RequestParam("time") String time ) {
//        MessageDAO.saveMessage(idChild,idUser,message,time);
//        JSONObject obj = new JSONObject();
//        obj.put("success","Pomyślnie wysłano wiadomość");
//        return obj;
//    }

}

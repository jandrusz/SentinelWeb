package com.sentinel.user.endpoint;

import com.sentinel.child.service.ChildService;
import com.sentinel.monitor.service.MonitorService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Monitor;
import com.sentinel.persistance.domain.User;
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
    public User register(User user) {
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
    public boolean removeChildFromObservation(User user, Child child) {
        return monitorService.deleteMonitorByIdUserAndIdChild(user.getIdUser(), child.getIdChild());
    }
}

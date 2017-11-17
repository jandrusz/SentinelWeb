package com.sentinel.user;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.monitor.MonitorService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Monitor;
import com.sentinel.persistance.domain.User;

@RestController
@RequestMapping(value = "user")
public class UserEndpoint {

	private UserService userService;

	private MonitorService monitorService;

	@Inject
	public UserEndpoint(UserService userService, MonitorService monitorService) {
		this.userService = userService;
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
		return monitorService.getUserChildrenUnderObservation(user.getIdUser());
	}

	@RequestMapping(value = "addChild", method = RequestMethod.POST)
	public Monitor addChildToObservation(User user, Child child) {
		return monitorService.addChildToObservation(user, child);
	}

	@RequestMapping(value = "removeChild", method = RequestMethod.POST)
	public boolean removeChildFromObservation(User user, Child child) {
		return monitorService.deleteMonitorByIdUserAndIdChild(user.getIdUser(), child.getIdChild());
	}
}

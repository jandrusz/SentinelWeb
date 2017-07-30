package com.sentinel.monitor.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.child.service.ChildService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Monitor;
import com.sentinel.persistance.domain.User;
import com.sentinel.persistance.repositories.MonitorRepository;

@Component
public class MonitorService {

	private MonitorRepository monitorRepository;

	private ChildService childService;

	@Inject
	public MonitorService(MonitorRepository monitorRepository, ChildService childService) {
		this.monitorRepository = monitorRepository;
		this.childService = childService;
	}

	public List<Child> getMonitorsOfUsersChildren(Integer idUser) {
		List<Monitor> monitors = monitorRepository.getMonitorsByIdUser(idUser);
		return monitors.stream()
				.map(m -> childService.getChildByIdChild(m.getIdChild()))
				.collect(Collectors.toList());
	}

	public Monitor addChildToObservation(User user, Child child) {
		Monitor monitor = Monitor.builder()
				.idUser(user.getIdUser())
				.idChild(childService.getChildByLoginAndPassword(child).getIdChild())
				.build();
		return monitorRepository.save(monitor);
	}

	public boolean deleteMonitorByIdUserAndIdChild(Integer idUser, Integer idChild) {
		return monitorRepository.deleteMonitorByIdUserAndIdChild(idUser, idChild) != 0;
	}

}

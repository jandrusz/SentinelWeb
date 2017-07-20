package com.sentinel.monitor.service;

import com.sentinel.persistance.domain.Monitor;
import com.sentinel.persistance.repositories.MonitorRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class MonitorService {

    private MonitorRepository monitorRepository;

    @Inject
    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List<Monitor> getMonitorsOfUsersChildren(Integer idUser) {
        return monitorRepository.getMonitorsByIdUser(idUser);
    }

    public Monitor save(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    public boolean deleteMonitorByIdUserAndIdChild(Integer idUser, Integer idChild) {
        return monitorRepository.deleteMonitorByIdUserAndIdChild(idUser, idChild) != 0;
    }

}
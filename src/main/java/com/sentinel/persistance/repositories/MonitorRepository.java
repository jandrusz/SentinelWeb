package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MonitorRepository extends JpaRepository<Monitor, Integer> {

    List<Monitor> getMonitorsByIdUser(Integer idUser);

    @Override
    Monitor save(Monitor monitor);

    Integer deleteMonitorByIdUserAndIdChild(Integer idUser, Integer idChild);
}

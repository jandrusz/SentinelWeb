package com.sentinel.persistance.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.Monitor;

@Transactional
public interface MonitorRepository extends JpaRepository<Monitor, Integer> {

	List<Monitor> getMonitorsByIdUser(Integer idUser);

	@Override
	Monitor save(Monitor monitor);

	Integer deleteMonitorByIdUserAndIdChild(Integer idUser, Integer idChild);
}

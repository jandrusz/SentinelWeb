package com.sentinel.persistance.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.sentinel.persistance.domain.Schedule;

@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	List<Schedule> getSchedulesByIdUser(Integer idUser);

	Schedule getScheduleByIdSchedule(Integer idSchedule);

	Integer removeScheduleByIdSchedule(Integer idSchedule);

	@Modifying
	@Query("UPDATE schedules s SET s.name = ?2 WHERE s.idSchedule = ?1")
	Integer editName(Integer idSchedule, String name);

	@Override
	Schedule save(Schedule schedule);

}

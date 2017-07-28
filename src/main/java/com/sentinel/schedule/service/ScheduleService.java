package com.sentinel.schedule.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.repositories.ScheduleRepository;

@Component
public class ScheduleService {

	private ScheduleRepository scheduleRepository;

	@Inject
	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	public List<Schedule> getSchedulesByIdUser(Integer idUser) {
		return scheduleRepository.getSchedulesByIdUser(idUser);
	}

	public boolean removeScheduleByIdSchedule(Integer idSchedule) {
		return scheduleRepository.removeScheduleByIdSchedule(idSchedule) != 0;
	}

	public boolean editName(Schedule schedule) {
		return scheduleRepository.editName(schedule.getIdSchedule(), schedule.getName()) != 0;
	}

	public Schedule getScheduleByIdSchedule(Integer idSchedule) {
		return scheduleRepository.getScheduleByIdSchedule(idSchedule);
	}

}

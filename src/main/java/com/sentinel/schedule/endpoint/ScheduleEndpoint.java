package com.sentinel.schedule.endpoint;


import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.child.service.ChildService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.domain.User;
import com.sentinel.schedule.service.ScheduleService;

@RestController
@RequestMapping(value = "schedule")
public class ScheduleEndpoint {

	private ScheduleService scheduleService;

	private ChildService childService;

	@Inject
	public ScheduleEndpoint(ScheduleService scheduleService, ChildService childService) {
		this.scheduleService = scheduleService;
		this.childService = childService;
	}

	@RequestMapping(value = "addSchedule", method = RequestMethod.POST)
	public Schedule addSchedule(Schedule schedule) {
		return scheduleService.saveSchedule(schedule);
	}


	@RequestMapping(value = "schedules", method = RequestMethod.GET)
	public List<Schedule> getSchedules(User user) {
		return scheduleService.getSchedulesByIdUser(user.getIdUser());
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public boolean remove(Schedule schedule) {
		return scheduleService.removeScheduleByIdSchedule(schedule.getIdSchedule());
	}

	@RequestMapping(value = "editName", method = RequestMethod.POST)
	public boolean editScheduleName(Schedule schedule) {
		return scheduleService.editName(schedule);
	}

	@RequestMapping(value = "childSchedule", method = RequestMethod.POST)
	public Schedule getSchedule(Child child) {
		return scheduleService.getScheduleByIdSchedule(child.getIdSchedule());
	}

	@RequestMapping(value = "bindChildToSchedule", method = RequestMethod.POST)
	public boolean bindChildToSchedule(Child child, Schedule schedule) {
		return childService.setSchedule(child.getIdChild(), schedule.getIdSchedule());
	}

}

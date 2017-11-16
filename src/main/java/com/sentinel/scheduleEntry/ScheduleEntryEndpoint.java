package com.sentinel.scheduleEntry;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.domain.ScheduleEntry;

@RestController
@RequestMapping(value = "scheduleEntry")
public class ScheduleEntryEndpoint {

	private ScheduleEntryService scheduleEntryService;

	@Inject
	public ScheduleEntryEndpoint(ScheduleEntryService scheduleEntryService) {
		this.scheduleEntryService = scheduleEntryService;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public boolean removeScheduleEntry(ScheduleEntry scheduleEntry) {
		return scheduleEntryService.removeScheduleEntryByIdScheduleEntry(scheduleEntry.getIdSchedule());
	}

	@RequestMapping(value = "scheduleEntries", method = RequestMethod.POST)
	public List<ScheduleEntry> getScheduleEntries(Schedule schedule) {
		return scheduleEntryService.getScheduleEntriesByIdSchedule(schedule);
	}

	@RequestMapping(value = "addScheduleEntry", method = RequestMethod.POST)
	public ScheduleEntry addOrEditScheduleEntry(ScheduleEntry scheduleEntry) {
		return scheduleEntryService.saveScheduleEntryOrUpdate(scheduleEntry);
	}

}

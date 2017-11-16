package com.sentinel.scheduleEntry;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.domain.ScheduleEntry;
import com.sentinel.persistance.repositories.ScheduleEntryRepository;

@Component
public class ScheduleEntryService {

	private ScheduleEntryRepository scheduleEntryRepository;

	@Inject
	public ScheduleEntryService(ScheduleEntryRepository scheduleEntryRepository) {
		this.scheduleEntryRepository = scheduleEntryRepository;
	}

	public boolean removeScheduleEntryByIdScheduleEntry(Integer idScheduleEntry) {
		return scheduleEntryRepository.removeScheduleEntryByIdScheduleEntry(idScheduleEntry) != 0;
	}

	public List<ScheduleEntry> getScheduleEntriesByIdSchedule(Schedule schedule) {
		return scheduleEntryRepository.getScheduleEntriesByIdSchedule(schedule.getIdSchedule());
	}

	public ScheduleEntry saveScheduleEntryOrUpdate(ScheduleEntry scheduleEntry) { //TODO can be done better
		try {
			scheduleEntryRepository.removeScheduleEntryByIdScheduleEntry(scheduleEntry.getIdScheduleEntry());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleEntryRepository.save(scheduleEntry);
	}
}

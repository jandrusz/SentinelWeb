package com.sentinel.persistance.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.ScheduleEntry;

@Transactional
public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Integer> {

	List<ScheduleEntry> getScheduleEntriesByIdSchedule(Integer idSchedule);

	Integer removeScheduleEntryByIdScheduleEntry(Integer idScheduleEntry);

	@Override
	ScheduleEntry save(ScheduleEntry scheduleEntry);
}

package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Integer> {

    List<ScheduleEntry> getScheduleEntriesByIdSchedule(Integer idSchedule);

    Integer removeScheduleEntryByIdScheduleEntry(Integer idScheduleEntry);

    @Override
    ScheduleEntry save(ScheduleEntry scheduleEntry);
}

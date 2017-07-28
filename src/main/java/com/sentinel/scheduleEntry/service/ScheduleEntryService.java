package com.sentinel.scheduleEntry.service;

import com.sentinel.persistance.domain.Schedule;
import com.sentinel.persistance.domain.ScheduleEntry;
import com.sentinel.persistance.repositories.ScheduleEntryRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

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

}

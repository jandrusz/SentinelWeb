package com.sentinel.schedule.service;

import com.sentinel.persistance.repositories.ScheduleRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Inject
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
}

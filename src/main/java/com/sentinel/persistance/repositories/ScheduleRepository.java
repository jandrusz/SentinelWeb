package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> getSchedulesByIdUser(String idUser);

    Schedule getScheduleByIdSchedule(Integer idSchedule);

    boolean removeScheduleByIdSchedule(Integer idSchedule);

    @Override
    Schedule save(Schedule schedule);

}

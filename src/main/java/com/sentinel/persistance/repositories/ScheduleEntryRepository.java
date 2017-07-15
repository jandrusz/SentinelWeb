package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Integer> {
}

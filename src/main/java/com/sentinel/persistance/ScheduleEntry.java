package com.sentinel.persistance;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "schedule_entries")
public class ScheduleEntry {
}

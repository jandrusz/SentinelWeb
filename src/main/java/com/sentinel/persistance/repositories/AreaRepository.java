package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface AreaRepository extends JpaRepository<Area, Integer> {


}

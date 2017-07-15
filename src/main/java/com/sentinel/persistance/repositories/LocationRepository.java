package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
interface LocationRepository extends JpaRepository<Location, Integer> {
}

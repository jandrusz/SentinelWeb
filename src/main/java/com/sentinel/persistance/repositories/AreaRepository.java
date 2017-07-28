package com.sentinel.persistance.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.Area;

@Transactional
public interface AreaRepository extends JpaRepository<Area, Integer> {

	Area getAreaByIdArea(Integer idArea);

	@Override
	Area save(Area area);

}

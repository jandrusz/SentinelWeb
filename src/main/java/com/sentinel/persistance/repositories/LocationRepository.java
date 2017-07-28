package com.sentinel.persistance.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.persistance.domain.Location;

@Transactional
public interface LocationRepository extends JpaRepository<Location, Integer> {

	@Override
	Location save(Location location);

	List<Location> getLocationsByIdChild(Integer idChild);

}

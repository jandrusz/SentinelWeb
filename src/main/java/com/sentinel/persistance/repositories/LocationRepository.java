package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Override
    Location save(Location location);

    List<Location> getLocationsByIdChild(Integer idChild);

}

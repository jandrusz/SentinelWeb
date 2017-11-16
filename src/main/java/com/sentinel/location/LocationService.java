package com.sentinel.location;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.Location;
import com.sentinel.persistance.repositories.LocationRepository;

@Component
public class LocationService {

	private LocationRepository locationRepository;

	@Inject
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}

	public List<Location> getLocationsByIdChild(Integer idChild) {
		return locationRepository.getLocationsByIdChild(idChild);
	}

}

package com.sentinel.area;


import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.sentinel.persistance.domain.Area;
import com.sentinel.persistance.repositories.AreaRepository;

@Component
public class AreaService {

	private AreaRepository areaRepository;

	@Inject
	public AreaService(AreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	public Area getAreaByIdArea(Integer idArea) {
		return areaRepository.getAreaByIdArea(idArea);
	}

	public Area saveArea(Area area) {
		return areaRepository.save(area);
	}

}

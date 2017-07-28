package com.sentinel.area.service;


import com.sentinel.persistance.domain.Area;
import com.sentinel.persistance.repositories.AreaRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AreaService {

    private AreaRepository areaRepository;

    @Inject
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Area getAreaByIdArea(Integer idArea){
        return areaRepository.getAreaByIdArea(idArea);
    }

    public Area saveArea(Area area){
        return areaRepository.save(area);
    }

}

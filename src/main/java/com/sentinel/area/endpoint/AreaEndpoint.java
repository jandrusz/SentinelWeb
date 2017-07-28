package com.sentinel.area.endpoint;

import com.sentinel.area.service.AreaService;
import com.sentinel.persistance.domain.Area;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "area")
public class AreaEndpoint {

    private AreaService areaService;

    @Inject
    public AreaEndpoint(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping(value = "addArea", method = RequestMethod.POST)
    public Area saveArea(Area area) {
        return areaService.saveArea(area);
    }

    @RequestMapping(value = "getArea", method = RequestMethod.GET)
    public Area getArea(Area area) {
        return areaService.getAreaByIdArea(area.getIdArea());
    }

}

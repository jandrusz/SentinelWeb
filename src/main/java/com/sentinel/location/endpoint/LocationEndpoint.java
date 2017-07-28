package com.sentinel.location.endpoint;

import com.sentinel.location.service.LocationService;
import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.domain.Location;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(value = "location")
public class LocationEndpoint {

    private LocationService locationService;

    @Inject
    public LocationEndpoint(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "locations", method = RequestMethod.POST)
    public List<Location> getLocations(Child child) {
        return locationService.getLocationsByIdChild(child.getIdChild());
    }

    @RequestMapping(value = "saveLocation", method = RequestMethod.POST)
    public Location saveLocation(Location location) {
        return locationService.saveLocation(location);
    }

//
//    @RequestMapping(value = "filteredLocations", method = RequestMethod.POST) //TODO for later development (WWW)
//    public JSONObject getFilteredLocations(@RequestParam("idChild") String idChild, @RequestParam("dateStart") String dateStart, @RequestParam("dateStop") String dateStop,
//                                           @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop) {
//        return LocationDAO.getLocations(idChild, dateStart, dateStop, timeStart, timeStop);
//    }
//
//    @RequestMapping(value = "checkChildPosition", method = RequestMethod.POST)
//    public JSONObject checkLocation(@RequestParam("idChild") String idChild) {
//        return LocationDAO.checkLocation(idChild);
//    }
//

}

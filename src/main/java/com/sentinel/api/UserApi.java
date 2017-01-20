package com.sentinel.api;

import com.sentinel.hibernate.dao.*;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserApi {

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public JSONObject logIn(@RequestParam("login") String login, @RequestParam("password") String password) {
		return UserDAO.getUserData(login, password);
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public JSONObject register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("login") String login,
			@RequestParam("password") String password) {
		return UserDAO.addUser(firstName, lastName, login, password);
	}

	@RequestMapping(value = "/schedules/scheduleEntries", method = RequestMethod.POST)
	public JSONObject getSchedule(@RequestParam("idSchedule") String idSchedule) {
		return ScheduleEntryDAO.getScheduleEntriesByScheduleId(idSchedule);
	}

	@RequestMapping(value = "/schedules", method = RequestMethod.POST)
	public JSONObject getSchedules(@RequestParam("idUser") String idUser) {
		return ScheduleDAO.getSchedulesByUserId(idUser);
	}

	@RequestMapping(value = "/children", method = RequestMethod.POST)
	public JSONObject getChildren(@RequestParam("idUser") String idUser) {
		return ChildDAO.getUserChildrenByUserId(idUser);
	}

	//TODO dodanie sprawdzania czy dany rodzic jest juz sparowany z dzieckiem
	@RequestMapping(value = "/children/addChild", method = RequestMethod.POST)
	public JSONObject addChild(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("idUser") String idUser) {
		return MonitorDAO.bindChildToParent(login, password, idUser);
	}

	@RequestMapping(value = "/children/removeChild", method = RequestMethod.POST)
	public JSONObject removeChild(@RequestParam("idUser") String idUser, @RequestParam("idChild") String idChild) {
		return MonitorDAO.unbindUserFromChild(idUser, idChild);
	}

	@RequestMapping(value = "/schedule/addScheduleEntry", method = RequestMethod.POST)
	public JSONObject addOrEditScheduleEntry(@RequestParam("name") String name, @RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop,
			@RequestParam("day") String day, @RequestParam("idSchedule") String idSchedule, @RequestParam("idScheduleEntry") String idScheduleEntry,
			@RequestParam("idArea") String idArea) {
		return ScheduleEntryDAO.addOrEditScheduleEntry(name, timeStart, timeStop, day, idSchedule, idScheduleEntry, idArea);
	}

	@RequestMapping(value = "/schedule/removeScheduleEntry", method = RequestMethod.POST)
	public JSONObject removeScheduleEntry(@RequestParam("scheduleEntryId") String scheduleEntryId) {
		return ScheduleEntryDAO.removeScheduleEntry(scheduleEntryId);
	}

	@RequestMapping(value = "/schedule/addSchedule", method = RequestMethod.POST)
	public JSONObject addSchedule(@RequestParam("name") String name, @RequestParam("idUser") String idUser) {
		return ScheduleDAO.addSchedule(name, idUser);
	}

	@RequestMapping(value = "/schedule/removeSchedule", method = RequestMethod.POST)
	public JSONObject removeSchedule(@RequestParam("idSchedule") String idSchedule) {
		return ScheduleDAO.removeSchedule(idSchedule);
	}

	@RequestMapping(value = "/schedule/editScheduleName", method = RequestMethod.POST)
	public JSONObject editScheduleName(@RequestParam("idSchedule") String idSchedule, @RequestParam("newName") String newName) {
		return ScheduleDAO.editScheduleName(idSchedule, newName);
	}

	@RequestMapping(value = "/schedule/bindChildToSchedule", method = RequestMethod.POST)
	public JSONObject bindChildToSchedule(@RequestParam("idChild") String idChild, @RequestParam("idSchedule") String idSchedule) {
		return ChildDAO.bindChildToSchedule(idChild, idSchedule);
	}

	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	public JSONObject getLocation(@RequestParam("idChild") String idChild) {
		return LocationDAO.getLocations(idChild);
	}

	@RequestMapping(value = "/filteredLocations", method = RequestMethod.POST)
	public JSONObject getFilteredLocations(@RequestParam("idChild") String idChild, @RequestParam("dateStart") String dateStart, @RequestParam("dateStop") String dateStop,
			@RequestParam("timeStart") String timeStart, @RequestParam("timeStop") String timeStop) {
		return LocationDAO.getLocations(idChild, dateStart, dateStop, timeStart, timeStop);
	}

	@RequestMapping(value = "/locations/checkChildPosition", method = RequestMethod.POST)
	public JSONObject checkLocation(@RequestParam("idChild") String idChild) {
		return LocationDAO.checkLocation(idChild);
	}

	@RequestMapping(value = "/area/addArea", method = RequestMethod.POST)
	public JSONObject saveArea(@RequestParam("areaLatitude") String areaLatitude, @RequestParam("areaLongitude") String areaLongitude,
			@RequestParam("areaRadius") String areaRadius) {
		return AreaDAO.saveArea(areaLatitude, areaLongitude, areaRadius);
	}

	@RequestMapping(value = "/area/getArea", method = RequestMethod.POST)
	public JSONObject getAreaForScheduleEntry(@RequestParam("idArea") String idArea) {
		return AreaDAO.getAreaForScheduleEntry(idArea);
	}

	@RequestMapping(value = "/messageReceiver", method = RequestMethod.POST)
	public JSONObject saveMessage(@RequestParam("idChild") String idChild, @RequestParam("userFirstName") String userFirstName, @RequestParam("message") String message,@RequestParam("time") String time ) {
		MessageDAO.saveMessage(idChild,userFirstName,message,time);
        JSONObject obj = new JSONObject();
        obj.put("success","Pomyślnie wysłano wiadomość");
        return obj;
	}

}

package com.sentinel.hibernate.dao;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import com.sentinel.hibernate.model.Area;
import com.sentinel.hibernate.model.Child;
import com.sentinel.hibernate.model.Location;
import com.sentinel.hibernate.model.ScheduleEntry;
import com.sentinel.hibernate.utils.HibernateUtil;
import com.sentinel.util.Parser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;

public class LocationDAO {

	public static JSONObject saveLocation(String longitude, String latitude, String day, String time, String idChild) {

		JSONObject obj = new JSONObject();
		Transaction tx = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Location location = new Location(Double.parseDouble(longitude), Double.parseDouble(latitude), day, time, Integer.parseInt(idChild));
			session.save(location);
			tx.commit();
			obj.put("success", "Zapisano");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			obj.put("failure", "Nie udało się zapisać");
		}
		return obj;
	}

	public static Location getLastLocation(String idChild) {

		JSONObject finalObj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		Location location;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Location where id = (select max(id) from Location where idChild = '" + idChild + "')";
			List results = session.createQuery(hql).list();
			location = (Location) results.get(0);
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}

		return location;
	}

	public static JSONObject getLocations(String idChild) {
		JSONObject obj2 = new JSONObject();
		JSONObject finalObj = new JSONObject();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Location where rownum <=5 and idChild = '" + idChild + "' order by id desc"; //TODO najwiekszych id albo najnowszych czasow/dni
			List results = session.createQuery(hql).list();
			List<Location> locations = new ArrayList<>();
			locations = results;

			if (locations.size() == 0) {
				finalObj.put("failure", "Brak lokalizacji w bazie danych");
				return finalObj;
			}

			for (int i = 0; i < locations.size(); i++) {
				String hql2 = "from Location where id = '" + locations.get(i).id + "'";
				List results2 = session.createQuery(hql2).list();
				Location location = (Location) results2.get(0);
				JSONObject obj = new JSONObject();


				obj.put("id", location.id.toString());
				obj.put("latitude", location.latitude);
				obj.put("longitude", location.longitude);
				obj.put("day", location.day);
				obj.put("time", location.time);
				obj2.put("location" + i, obj);
			}
			finalObj.put("success", obj2);
			return finalObj;

		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	public static JSONObject checkLocation(String idChild) {
		JSONObject finalObj = new JSONObject();
		try {

			Child child = ChildDAO.getChild(idChild);
			LocalDateTime now = LocalDateTime.now();

			ScheduleEntry scheduleEntry = ScheduleEntryDAO
					.getScheduleEntryToCheckLocalization(child.idSchedule.toString(), Parser.getDayInPolish(now.getDayOfWeek().toString()), now.getHour(), now.getMinute());

			Area area = AreaDAO.getArea(scheduleEntry.idArea); //wyciagam area z bazy i robie porownanie z localization dziecka
			Location location = LocationDAO.getLastLocation(idChild);

			if (checkChildLocalization(area, location)) {
				finalObj.put("success", "Dziecko zgubione");
			} else {
				finalObj.put("failure", "Wszystko ok");
			}
		} catch (Exception e) {
			finalObj.put("failure", "Zapewne wszystko ok - nie ma teraz przypisanego harmonogramu");
			return finalObj;
		}

		return finalObj;
	}

	private static boolean checkChildLocalization(Area area, Location location) {

		double areaLatitude = area.latitude * 110574;
		double locationLatitude = location.latitude * 110574;
		double areaLongitude = area.longitude * 111320 * cos(Math.toRadians(area.latitude));
		double locationLongitude = location.longitude * 111320 * cos(Math.toRadians(location.latitude));

		if (sqrt(pow(areaLatitude - locationLatitude, 2) + pow(areaLongitude - locationLongitude, 2)) > area.radius) {
			return true;
		}
		return false;
	}

	public static JSONObject getLocations(String idChild, String dateStart, String dateStop, String timeStart, String timeStop) {
		JSONObject obj2 = new JSONObject();
		JSONObject finalObj = new JSONObject();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			dateStop = dateStop.isEmpty() ? LocalDateTime.now().toLocalDate().toString() : dateStop;
			dateStart = dateStart.isEmpty() ? "2000-01-01" : dateStart;
			timeStart = timeStart.isEmpty() ? "00:00:00" : timeStart;
			timeStop = timeStop.isEmpty() ? "23:59:59" : timeStop;

			Criteria criteria = session.createCriteria(Location.class);
			criteria.add(Restrictions.eq("idChild", Integer.parseInt(idChild)));
			criteria.add(Restrictions.between("day", dateStart, dateStop));
			criteria.add(Restrictions.between("time", timeStart, timeStop));

			List<Location> locations = criteria.list();

			if (locations.size() == 0) {
				finalObj.put("failure", "Brak lokalizacji w bazie danych");
				return finalObj;
			}

			for (int i = 0; i < locations.size(); i++) {
				String hql2 = "from Location where id = '" + locations.get(i).id + "'";
				List results2 = session.createQuery(hql2).list();
				Location location = (Location) results2.get(0);
				JSONObject obj = new JSONObject();


				obj.put("id", location.id.toString());
				obj.put("latitude", location.latitude);
				obj.put("longitude", location.longitude);
				obj.put("day", location.day);
				obj.put("time", location.time);
				obj2.put("location" + i, obj);
			}
			finalObj.put("success", obj2);
			return finalObj;

		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

}

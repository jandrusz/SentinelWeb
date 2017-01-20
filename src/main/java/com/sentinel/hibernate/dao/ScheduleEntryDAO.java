package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.ScheduleEntry;
import com.sentinel.hibernate.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ScheduleEntryDAO {

	private static JSONObject finalObj;
	private static JSONObject obj2;

	public static JSONObject getScheduleEntriesByScheduleId(String idSchedule) {
		finalObj = new JSONObject();
		obj2 = new JSONObject();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String scheduleEntries = "from ScheduleEntry where idSchedule = '" + idSchedule + "'";
			List scheduleEntriesResults = session.createQuery(scheduleEntries).list();
			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < scheduleEntriesResults.size(); i++) {
				ScheduleEntry scheduleEntry = (ScheduleEntry) scheduleEntriesResults.get(i);
				jsonArray.add(scheduleEntry);
			}

			finalObj.put("success", jsonArray);
			return finalObj;

		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	@Deprecated
	private static void getScheduleEntry(ScheduleEntry scheduleEntry, int i) {
		JSONObject obj = new JSONObject();
		obj.put("id", scheduleEntry.id);
		obj.put("timeStart", scheduleEntry.timeStart);
		obj.put("timeStop", scheduleEntry.timeStop);
		obj.put("name", scheduleEntry.name);
		obj.put("day", scheduleEntry.day);
		obj.put("idSchedule", scheduleEntry.idSchedule);
		obj.put("idArea", scheduleEntry.idArea);
		obj2.put("scheduleEntry" + i, obj);
	}

	public static JSONObject addOrEditScheduleEntry(String name, String timeStart, String timeStop, String day, String idSchedule, String idScheduleEntry, String idArea) {
		finalObj = new JSONObject();

		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			if (idScheduleEntry.isEmpty()) {
				ScheduleEntry scheduleEntry = new ScheduleEntry(name, timeStart, timeStop, day, Integer.valueOf(idSchedule), Integer.parseInt(idArea));
				session.save(scheduleEntry);
				finalObj.put("success", "Dodano wpis");
			} else {
				String hql =
						"update ScheduleEntry set name = '" + name + "', timeStart = '" + timeStart + "', timeStop = '" + timeStop + "',day = '" + day + "', idArea = '" + idArea +
								"'  where id = " + idScheduleEntry + "";
				Query q = session.createQuery(hql);
				q.executeUpdate();
				finalObj.put("success", "Zaktualizowano wpis");
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Nie dodano wpisu");
		}

		return finalObj;
	}

	public static void deleteEntriesFromSchedule(String idSchedule) {

		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String hql = "delete from ScheduleEntry where idSchedule = '" + idSchedule + "'";
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public static JSONObject removeScheduleEntry(String scheduleEntryId) {
		finalObj = new JSONObject();

		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String hql = "delete from ScheduleEntry where id = '" + scheduleEntryId + "'";
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Nie udało się usunąć");
		}
		finalObj.put("success", "Usunięto");
		return finalObj;
	}

	public static ScheduleEntry getScheduleEntryToCheckLocalization(String idSchedule, String day, int hour, int minutes) {


		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ScheduleEntry where idSchedule = '" + idSchedule + "' and day= '" + day + "' and '" + hour + ":" + minutes + "' between timeStart and timeStop";
			List results = session.createQuery(hql).list();
			return (ScheduleEntry) results.get(0);
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}

	}

}
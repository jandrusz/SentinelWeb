package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Child;
import com.sentinel.hibernate.model.Monitor;
import com.sentinel.hibernate.model.Schedule;
import com.sentinel.hibernate.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

public class ScheduleDAO {


	public static JSONObject getSchedulesByUserId(String idUser) {

		JSONObject finalObj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject obj;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Schedule where idUser = '" + idUser + "'";
			List results = session.createQuery(hql).list();
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules = results;

			List<Schedule> schedules2 = getUserChildrenByUserId(idUser);

			for (int i = 0; i < schedules2.size(); i++) {
				if (!schedules.contains(schedules2.get(i))) {
					schedules.add(schedules2.get(i));
				}
			}

			for (int i = 0; i < schedules.size(); i++) {
				obj = new JSONObject();
				obj.put("idSchedule", schedules.get(i).idSchedule);
				obj.put("name", schedules.get(i).name);
				obj.put("idUser", schedules.get(i).idUser);
				obj2.put("schedule" + i, obj);
			}


			finalObj.put("success", obj2);
			return finalObj;
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	public static List<Schedule> getUserChildrenByUserId(String idUser) {

		List<Schedule> schedules = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Criteria criteria = session.createCriteria(Monitor.class);
			criteria.add(Restrictions.eq("idUser", Integer.parseInt(idUser)));

			List<Monitor> monitors = criteria.list();

			for (int i = 0; i < monitors.size(); i++) {
				String hql2 = "from Child where id = '" + monitors.get(i).idChild + "'";
				List results2 = session.createQuery(hql2).list();
				Child child = (Child) results2.get(0);
				if (child.idSchedule != 0) {
					Schedule schedule = getChildSchedule(child);
					if (!schedules.contains(schedule)) {
						schedules.add(schedule);
					}
				}
			}
			return schedules;

		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}


	private static Schedule getChildSchedule(Child child) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Schedule where id = '" + child.idSchedule + "'";
			List results = session.createQuery(hql).list();
			Schedule schedule = (Schedule) results.get(0);
			return schedule;
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	public static Schedule getScheduleById(String idSchedule) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Schedule where id = '" + idSchedule + "'";
			List results = session.createQuery(hql).list();
			Schedule schedule = (Schedule) results.get(0);
			return schedule;
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}

	}

	public static JSONObject addSchedule(String name, String idUser) {
		JSONObject finalObj = new JSONObject();


		Transaction tx = null;
		{
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				tx = session.beginTransaction();
				Schedule schedule = new Schedule(name, Integer.valueOf(idUser));
				session.save(schedule);
				tx.commit();
				finalObj.put("success", "Zapisano pomyślnie");
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				finalObj.put("failure", "Coś poszło nie tak");
			}

			return finalObj;
		}
	}

	public static JSONObject removeSchedule(String idSchedule) {

		JSONObject finalObj = new JSONObject();
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String hql = "delete from Schedule where id = '" + idSchedule + "'";
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
			ChildDAO.setIdScheduleToDefaultValue(idSchedule);
			ScheduleEntryDAO.deleteEntriesFromSchedule(idSchedule);
			finalObj.put("success", "Usunięto");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Coś poszło nie tak");
		}

		return finalObj;
	}

	public static JSONObject editScheduleName(String idSchedule, String newName) {
		JSONObject finalObj = new JSONObject();
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String hql = "update Schedule set name = '" + newName + "' where id = '" + idSchedule + "'";
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
			finalObj.put("success", "Zaktualizowano");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Coś poszło nie tak");
		}

		return finalObj;

	}

	public static JSONObject getScheduleByIdForChild(String idChild) {

		JSONObject finalObj = new JSONObject();
		JSONObject obj = new JSONObject();

		Schedule schedule = new Schedule();
		Child child = ChildDAO.getChild(idChild);
		try {
			schedule = getChildSchedule(child);
			obj.put("idSchedule", schedule.idSchedule);
			obj.put("name", schedule.name);
			obj.put("idUser", schedule.idUser);
			finalObj.put("success", obj);
		} catch (Exception e) {
			finalObj.put("failure", "Nie masz przypisanego harmongramu");
		}

		return finalObj;
	}

}

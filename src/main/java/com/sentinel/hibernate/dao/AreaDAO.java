package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Area;
import com.sentinel.hibernate.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

public class AreaDAO {


	public static JSONObject saveArea(String latitude, String longitude, String radius) {

		JSONObject finalObj = new JSONObject();
		JSONObject obj = new JSONObject();
		Transaction tx = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Area area = new Area(Double.parseDouble(longitude), Double.parseDouble(latitude), Float.parseFloat(radius));
			session.save(area);
			Integer id = (Integer) session.save(area);
			tx.commit();

			obj.put("id", id.toString());
			finalObj.put("success", obj);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Nie udało się zapisać");
		}
		return finalObj;
	}

	public static Area getArea(Integer idArea) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Area where id = '" + idArea + "'";
			List results = session.createQuery(hql).list();
			Area area = (Area) results.get(0);
			return area;
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}

	}

	public static JSONObject getAreaForScheduleEntry(String idArea) {

		JSONObject obj = new JSONObject();
		JSONObject finalObj = new JSONObject();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Area where id = '" + idArea + "'";
			List results = session.createQuery(hql).list();
			Area area = (Area) results.get(0);
			obj.put("id", area.id.toString());
			obj.put("latitude", area.latitude.toString());
			obj.put("longitude", area.longitude.toString());
			obj.put("radius", area.radius.toString());
			finalObj.put("success", obj);
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
		return finalObj;
	}

}

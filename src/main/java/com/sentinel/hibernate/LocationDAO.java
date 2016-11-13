package com.sentinel.hibernate;

import com.sentinel.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.List;

public class LocationDAO {

    public static JSONObject saveLocation(Integer idLocation, String longitude, String latitude, String day, String time, String idChild) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Location location = new Location(idLocation, Double.parseDouble(longitude), Double.parseDouble(latitude), day, time, Integer.parseInt(idChild));
            session.save(location);
            tx.commit();
            obj.put("success", "Udało się zapisać.");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            obj.put("failure", "Nie udało się zapisać.");
        }
        return obj;
    }

    public static JSONObject getLocation(String idChild) {

        JSONObject finalObj = new JSONObject();
        JSONObject obj = new JSONObject();
        Location location;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Location where time = (select max(time) from Location where idChild = '" + idChild + "') and day = (select max(day) from Location where idChild = '" + idChild + "')";
            List results = session.createQuery(hql)
                    .list();
            location = (Location) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }

        obj.put("latitude", location.latitude);
        obj.put("longitude", location.longitude);
        obj.put("day", location.day);
        obj.put("time", location.time);
        finalObj.put("success", obj);

        return finalObj;
    }


}

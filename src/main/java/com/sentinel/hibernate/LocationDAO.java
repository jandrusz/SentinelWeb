package com.sentinel.hibernate;

import com.sentinel.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
            if (tx != null) tx.rollback();
            obj.put("failure", "Nie udało się zapisać");
        }
        return obj;
    }

    @Deprecated
    public static JSONObject getLocation(String idChild) {

        JSONObject finalObj = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        Location location;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Location where id = (select max(id) from Location where idChild = '" + idChild + "')";
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
        obj2.put("location0", obj);


        finalObj.put("success", obj2);

        return finalObj;
    }

    public static JSONObject getLocations(String idChild) {
        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Location where rownum <=5 and idChild = '" + idChild + "' order by id desc"; //TODO najwiekszych id albo najnowszych czasow/dni
            List results = session.createQuery(hql)
                    .list();
            List<Location> locations = new ArrayList<>();
            locations = results;

            if (locations.size() == 0) {
                finalObj.put("failure", "Brak lokalizacji w bazie danych");
                return finalObj;
            }

            for (int i = 0; i < locations.size(); i++) {
                String hql2 = "from Location where id = '" + locations.get(i).id + "'";
                List results2 = session.createQuery(hql2)
                        .list();
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

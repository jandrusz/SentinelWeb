package com.sentinel.hibernate;

import com.sentinel.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import java.util.List;

public class ChildLocationDAO {


    public static JSONObject saveChildAtLocation(Integer idChild, Integer idLocation) {

        return new JSONObject();
    }

    public static void getLocationId(double longitude, double latitude, String day, String time, Integer idChild) {

        Integer idLocation = 0;


        saveChildAtLocation(idChild, idLocation);
    }


    public static JSONObject getLocation() {

        JSONObject finalObj = new JSONObject();
        JSONObject obj = new JSONObject();
        String id = "101";
        Location location;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Location where id = '" + id + "'";
            List results = session.createQuery(hql)
                    .list();
            location = (Location) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }

        obj.put("latitude", location.latitude);
        obj.put("longitude", location.longitude);
        finalObj.put("success", obj);

        return finalObj;
    }


}

package com.sentinel.hibernate;

import com.sentinel.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

public class LocationDAO {

    public static JSONObject saveLocation(Integer idLocation, String longitude, String latitude, String day, String time, String idChild) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Location location = new Location(idLocation, Double.parseDouble(longitude), Double.parseDouble(latitude), day, time);
            session.save(location);
            tx.commit();

            ChildLocationDAO.getLocationId(Double.parseDouble(longitude), Double.parseDouble(latitude), day, time, Integer.parseInt(idChild));

            //TODO uzyskac id wstawionego Location w tym miejscu
            obj.put("success", "Zarejestrowano pomyślnie, możesz się zalogować.");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            obj.put("failure", "Nie udało się zarejestrować.");
        }
        return obj;
    }


}

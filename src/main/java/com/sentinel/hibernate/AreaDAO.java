package com.sentinel.hibernate;

import com.sentinel.model.Area;
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
            Area area = new Area(Double.parseDouble(latitude), Double.parseDouble(longitude), Float.parseFloat(radius));
            session.save(area);
            Integer id = (Integer) session.save(area);
            tx.commit();

            obj.put("id", id.toString()); //TODO
            finalObj.put("success", obj);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Nie udało się zapisać");
        }
        return finalObj;
    }

}

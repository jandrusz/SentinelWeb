package com.sentinel.hibernate;

import com.sentinel.model.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {


    public static JSONObject getSchedulesByUserId(String idUser) {

        JSONObject finalObj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject obj;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Schedule where idUser = '" + idUser + "'";
            List results = session.createQuery(hql)
                    .list();
            List<Schedule> schedules = new ArrayList<Schedule>();
            schedules = results;

            for (int i = 0; i < results.size(); i++) {
                obj = new JSONObject();
                obj.put("idSchedule", schedules.get(i).id);
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
                if (tx != null) tx.rollback();
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
            if (tx != null) tx.rollback();
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
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Coś poszło nie tak");
        }

        return finalObj;

    }

}

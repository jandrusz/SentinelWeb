package com.sentinel.hibernate;

import com.sentinel.model.ScheduleEntry;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import java.util.List;

public class ScheduleEntryDAO {

    private static JSONObject finalObj;
    private static JSONObject obj2;

    public static JSONObject getScheduleEntriesByScheduleId(String idSchedule) {
        finalObj = new JSONObject();
        obj2 = new JSONObject();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String scheduleEntries = "from ScheduleEntry where idSchedule = '" + idSchedule + "'";
            List scheduleEntriesResults = session.createQuery(scheduleEntries)
                    .list();

            for (int i = 0; i < scheduleEntriesResults.size(); i++) {
                ScheduleEntry scheduleEntry = (ScheduleEntry) scheduleEntriesResults.get(i);
                getScheduleEntry(scheduleEntry, i);
            }

            if (scheduleEntriesResults.size() == 0) {
                finalObj.put("failure", "Cos poszło nie tak");
                return finalObj;
            }

            finalObj.put("success", obj2);
            return finalObj;

        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }


    private static void getScheduleEntry(ScheduleEntry scheduleEntry, int i) {
        JSONObject obj = new JSONObject();
        obj.put("id", scheduleEntry.id);
        obj.put("timeStart", scheduleEntry.timeStart);
        obj.put("timeStop", scheduleEntry.timeStop);
        obj.put("name", scheduleEntry.name);
        obj.put("day", scheduleEntry.day);
        obj.put("idSchedule", scheduleEntry.idSchedule);
        obj2.put("scheduleEntry" + i, obj);
    }

    public static JSONObject addOrEditScheduleEntry(String name, String timeStart, String timeStop, String day, String idSchedule, String idScheduleEntry) {
        finalObj = new JSONObject();

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            if (idScheduleEntry.isEmpty()) {
                ScheduleEntry scheduleEntry = new ScheduleEntry(0, name, timeStart, timeStop, day, Integer.valueOf(idSchedule), 0);
                session.save(scheduleEntry);
            } else {
                String hql = "update ScheduleEntry set name = '" + name + "', timeStart = '" + timeStart + "', timeStop = '" + timeStop + "',day = '" + day + "' where id = " + idScheduleEntry + "";
                Query q = session.createQuery(hql);
                q.executeUpdate();
            }
            tx.commit();
            finalObj.put("success", "dodano wpis");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "nie dodano wpisu");
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
            if (tx != null) tx.rollback();
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
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Nie udało się usunąć");
        }
        finalObj.put("success", "Usunięto");
        return finalObj;
    }

}
package com.sentinel.hibernate;

import com.sentinel.model.ScheduleEntry;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    //TODO tutaj dodanie wpisu do bazy danych
    public static JSONObject addScheduleEntry(String name, String timeStart, String timeStop, String day, String idSchedule) {
        finalObj = new JSONObject();

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ScheduleEntry scheduleEntry = new ScheduleEntry(0, name, timeStart, timeStop, day, Integer.valueOf(idSchedule), 0);
            session.save(scheduleEntry);
            tx.commit();
            finalObj.put("success", "dodano wpis");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "nie dodano wpisu");
        }

        return finalObj;
    }
}
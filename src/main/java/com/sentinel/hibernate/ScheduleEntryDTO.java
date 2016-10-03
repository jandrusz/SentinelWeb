package com.sentinel.hibernate;

import com.sentinel.model.Schedule;
import com.sentinel.model.ScheduleEntry;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import java.util.List;

public class ScheduleEntryDTO {

    private static JSONObject finalObj = new JSONObject();
    private static JSONObject obj2 = new JSONObject();

    public static JSONObject getScheduleEntries(Schedule schedule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String scheduleEntries = "from ScheduleEntry where idUser = '" + schedule.idUser + "'";
            List scheduleEntriesResults = session.createQuery(scheduleEntries)
                    .list();

            for (int i = 0; i < scheduleEntriesResults.size(); i++) {
                ScheduleEntry scheduleEntry = (ScheduleEntry) scheduleEntriesResults.get(i);
                addScheduleEntryToJson(scheduleEntry, i);
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


    private static void addScheduleEntryToJson(ScheduleEntry scheduleEntry, int i) {

        JSONObject obj = new JSONObject();
        obj.put("timeStart", scheduleEntry.timeStart);
        obj.put("timeStop", scheduleEntry.timeStop);
        obj.put("name", scheduleEntry.name);
        obj2.put("scheduleEntry" + i, obj);
    }

}

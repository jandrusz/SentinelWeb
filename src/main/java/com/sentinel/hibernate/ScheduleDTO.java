package com.sentinel.hibernate;

import com.sentinel.model.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import java.util.List;

public class ScheduleDTO {


    public static JSONObject getSchedule(String idChild, String idUser) {

        Schedule childSchedule;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String childScheduleId = "from Schedule where idUser = '" + idUser + "'";
            List childSchedules = session.createQuery(childScheduleId)
                    .list();

            childSchedule = (Schedule) childSchedules.get(0);
            return ScheduleEntryDTO.getScheduleEntries(childSchedule);

        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

}

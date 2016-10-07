package com.sentinel.hibernate;

import com.sentinel.model.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import java.util.List;

public class ScheduleDAO {


    public static JSONObject getScheduleByChildId(String idChild) {

        Schedule childSchedule;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String childScheduleId = "from Schedule where idChild = '" + idChild + "'";
            List childSchedules = session.createQuery(childScheduleId)
                    .list();

            childSchedule = (Schedule) childSchedules.get(0);
            return ScheduleEntryDAO.getScheduleEntriesBySchedule(childSchedule);

        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

}

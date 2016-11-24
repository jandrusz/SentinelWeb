package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Area;
import com.sentinel.hibernate.model.Child;
import com.sentinel.hibernate.model.Location;
import com.sentinel.hibernate.model.ScheduleEntry;
import com.sentinel.hibernate.utils.HibernateUtil;
import com.sentinel.utils.Parser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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

    public static Location getLastLocation(String idChild) {

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

        return location;
    }

    public static JSONObject getLocations(String idChild) {
        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Location where rownum <=1 and idChild = '" + idChild + "' order by id desc"; //TODO najwiekszych id albo najnowszych czasow/dni
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

    public static JSONObject checkLocation(String idChild) {
        JSONObject finalObj = new JSONObject();
        try {

            Child child = ChildDAO.getChild(idChild);
            LocalDateTime now = LocalDateTime.now();

            ScheduleEntry scheduleEntry = ScheduleEntryDAO.getScheduleEntryToCheckLocalization(child.idSchedule.toString(), Parser.getDayInPolish(now.getDayOfWeek().toString()), now.getHour(), now.getMinute());

            Area area = AreaDAO.getArea(scheduleEntry.idArea); //wyciagam area z bazy i robie porownanie z localization dziecka
            Location location = LocationDAO.getLastLocation(idChild);

            if (checkChildLocalization(area, location))
                finalObj.put("success", "Dziecko zgubione");
            else
                finalObj.put("failure", "Wszystko ok");
        } catch (Exception e) {
            finalObj.put("failure", "Zapewne wszystko ok - nie ma teraz przypisanego harmonogramu");
            return finalObj;
        }

        return finalObj;
    }

    private static boolean checkChildLocalization(Area area, Location location) { //do przeliczenia
        if (sqrt(pow(area.latitude - location.latitude, 2) + pow(area.longitude - location.longitude, 2)) > area.radius * 0.001)
            return true;
        return false;
    }

}

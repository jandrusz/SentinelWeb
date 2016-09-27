package com.sentinel.hibernate;

import com.sentinel.persistance.Child;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MonitorDTO {

    public static JSONObject getChildrenByUserEmail(String email) {

        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();
        Child child;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String numberOfChildren = "select count(*) from Monitor where idUser = (select id from User where email='" + email + "')";
            List numberOfChildrenResult = session.createQuery(numberOfChildren)
                    .list();


            List<String> listOfUserIds = new ArrayList<>();

            if (Integer.valueOf(numberOfChildrenResult.get(0).toString()) > 0) {
                String usersIds = "select idChild from Monitor where idUser = (select id from User where email='" + email + "')";
                List usersIdsResult = session.createQuery(usersIds)
                        .list();
                for (int i = 0; i < Integer.valueOf(numberOfChildrenResult.get(0).toString()); i++) {
                    listOfUserIds.add(usersIdsResult.get(i).toString());
                }

                for (int i = 0; i < listOfUserIds.size(); i++) {
                    String childName = "from Child where id = '" + listOfUserIds.get(i) + "'";
                    List childNameResult = session.createQuery(childName)
                            .list();
                    child = (Child) childNameResult.get(0);
                    JSONObject obj = new JSONObject();
                    obj.put("id", child.id.toString());
                    obj.put("firstName", child.firstName);
                    obj.put("lastName", child.lastName);
                    obj.put("email", child.email);
                    obj.put("password", child.password);
                    obj.put("idSchedule", child.idSchedule.toString());
                    obj2.put("child" + i, obj);

                }
                finalObj.put("success", obj2);
                return finalObj;
            }
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        finalObj.put("failure", "Brak dzieci w bazie danych");
        return finalObj;

    }


}

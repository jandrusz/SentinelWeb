package com.sentinel.hibernate;

import com.sentinel.model.Child;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChildDTO {

    public static JSONObject addChild(Integer id, String firstName, String lastName, String email, String idUser) {
        JSONObject finalObj = new JSONObject();
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Child child = new Child(id, firstName, lastName, email, null, null);
            session.save(child);
            tx.commit();
            MonitorDTO.bindChildToParent(Integer.parseInt(idUser), ChildDTO.getChildId(email));
            finalObj.put("success", "Dodano dziecko.");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Nie udało się dodać dziecka.");
        }
        return finalObj;
    }

    public static JSONObject getChildrenByUserEmail(String idUser) {

        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();
        Child child;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Integer numberOfChildren = MonitorDTO.getNumberOfChildrenForUser(idUser);

            List<String> listOfUserIds = new ArrayList<>();

            if (numberOfChildren > 0) {
                String usersIds = "select idChild from Monitor where idUser = '" + idUser + "')";
                List usersIdsResult = session.createQuery(usersIds)
                        .list();
                for (int i = 0; i < numberOfChildren; i++) {
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

    static Integer getChildId(String email) {

        return null;
    }

}

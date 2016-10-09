package com.sentinel.hibernate;

import com.sentinel.model.Child;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChildDAO {

    public static JSONObject bindUserToChild(Integer id, String firstName, String lastName, String login, String idUser) {
        JSONObject finalObj = new JSONObject();
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Child child = new Child(id, firstName, lastName, login, null, null);
            session.save(child);
            tx.commit();
            MonitorDAO.bindChildToParent(Integer.parseInt(idUser), ChildDAO.getChildId(login));
            finalObj.put("success", "Dodano dziecko.");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Nie udało się dodać dziecka.");
        }
        return finalObj;
    }

    public static JSONObject getUserChildrenByUserId(String idUser) {

        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();
        Child child;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Integer numberOfChildren = MonitorDAO.getNumberOfChildrenForUser(idUser);

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
                    obj.put("login", child.login);
                    obj.put("password", child.password == null ? "" : child.password);
                    obj.put("idSchedule", child.idSchedule == null ? "" : child.idSchedule.toString());
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

    private static Integer getChildId(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select id FROM Child where login = '" + login + "'";
            List results = session.createQuery(hql)
                    .list();
            return (Integer) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    //TODO gdy bedzie aplikacja dziecka
    public static JSONObject getChildData(String login, String password) {
        return null;
    }

}

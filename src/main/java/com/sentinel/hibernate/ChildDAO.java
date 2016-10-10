package com.sentinel.hibernate;

import com.sentinel.model.Child;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChildDAO {


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

    public static Integer getChildId(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select id FROM Child where login = '" + login + "'";
            List results = session.createQuery(hql)
                    .list();
            return (Integer) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public static JSONObject getChildData(String login, String password) {
        JSONObject obj = new JSONObject();
        JSONObject finalObj = new JSONObject();
        try {
            Child child = getChild(login, password);
            obj.put("id", child.id.toString());
            obj.put("firstName", child.firstName);
            obj.put("lastName", child.lastName);
            obj.put("login", child.login);
            obj.put("password", child.password);
            finalObj.put("success", obj);
        } catch (Exception e) {
            finalObj.put("failure", "Nie ma takiego użytkownika");
            return finalObj;
        }
        return finalObj;
    }

    private static Child getChild(String login, String password) {
        Child child;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Child where login = '" + login + "' and password = '" + password + "'";
            List results = session.createQuery(hql)
                    .list();
            child = (Child) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        return child;
    }

    public static boolean checkIfCredentialsAreCorrect(String login, String password) {
        if (getChild(login, password) != null)
            return true;
        return false;
    }

    public static JSONObject addChild(Integer id, String firstName, String lastName, String login, String password) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;
        if (!isLoginInDatabase(login)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                Child child = new Child(id, firstName, lastName, login, password, null);
                session.save(child);
                tx.commit();
                obj.put("success", "Zarejestrowano pomyślnie, możesz się zalogować.");
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                obj.put("failure", "Nie udało się zarejestrować.");
            }
        } else {
            obj.put("failure", "Niepowodzenie, podany login już istnieje w bazie danych.");
        }
        return obj;
    }

    private static boolean isLoginInDatabase(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Child where login = '" + login + "'";
        List results = session.createQuery(hql)
                .list();
        session.close();
        return results.size() > 0;
    }

}

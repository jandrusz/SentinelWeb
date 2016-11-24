package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Child;
import com.sentinel.hibernate.model.Monitor;
import com.sentinel.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChildDAO {

    public static JSONObject getUserChildrenByUserId(String idUser) {

        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Monitor where idUser = '" + idUser + "'";
            List results = session.createQuery(hql)
                    .list();
            List<Monitor> monitors = new ArrayList<>();
            monitors = results;

            if (monitors.size() == 0) {
                finalObj.put("failure", "Brak dzieci w bazie danych");
                return finalObj;
            }

            for (int i = 0; i < monitors.size(); i++) {
                String hql2 = "from Child where id = '" + monitors.get(i).idChild + "'";
                List results2 = session.createQuery(hql2)
                        .list();
                Child child = (Child) results2.get(0);
                JSONObject obj = new JSONObject();
                obj.put("id", child.id.toString());
                obj.put("firstName", child.firstName);
                obj.put("lastName", child.lastName);
                obj.put("login", child.login);
                obj.put("password", child.password == null ? "" : child.password);
                obj.put("idSchedule", child.idSchedule == null ? "0" : child.idSchedule.toString()); //jesli schedule jest null to ustawiamy zero
                obj2.put("child" + i, obj);
            }
            finalObj.put("success", obj2);
            return finalObj;

        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
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

    public static JSONObject addChild(String firstName, String lastName, String login, String password) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;
        if (!isLoginInDatabase(login)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                Child child = new Child(firstName, lastName, login, password, 0);
                session.save(child);
                tx.commit();
                obj.put("success", "Zarejestrowano pomyślnie, możesz się zalogować");
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                obj.put("failure", "Nie udało się zarejestrować");
            }
        } else {
            obj.put("failure", "Niepowodzenie, podany login już istnieje w bazie danych");
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

    public static JSONObject bindChildToSchedule(String idChild, String idSchedule) {

        JSONObject finalObj = new JSONObject();
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            String hql = "update Child set idSchedule = '" + idSchedule + "' where id = '" + idChild + "'";
            Query q = session.createQuery(hql);
            q.executeUpdate();
            tx.commit();
            finalObj.put("success", "Zaktualizowano");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            finalObj.put("failure", "Coś poszło nie tak");
        }

        return finalObj;

    }

    public static void setIdScheduleToDefaultValue(String idSchedule) {
        JSONObject finalObj = new JSONObject();
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            String hql = "update Child set idSchedule = 0 where idSchedule = '" + idSchedule + "'";
            Query q = session.createQuery(hql);
            q.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
    }

    public static Child getChild(String idChild) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Child where id = '" + idChild + "'";
            List results = session.createQuery(hql)
                    .list();
            Child child = (Child) results.get(0);
            return child;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }

    }

}

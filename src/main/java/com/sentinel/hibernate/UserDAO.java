package com.sentinel.hibernate;

import com.sentinel.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.List;

public class UserDAO {

    public static JSONObject addUser(Integer id, String firstName, String lastName, String login, String password) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;
        if (!isloginInDatabase(login)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                User user = new User(id, firstName, lastName, login, password);
                session.save(user);
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

    private static boolean isloginInDatabase(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM User where login = '" + login + "'";
        List results = session.createQuery(hql)
                .list();
        session.close();
        return results.size() > 0;
    }

    private static User getUser(String login, String password) {
        User user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from User where login = '" + login + "' and password = '" + password + "'";
            List results = session.createQuery(hql)
                    .list();
            user = (User) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        return user;
    }

    public static JSONObject getUserData(String login, String password) {
        JSONObject obj = new JSONObject();
        JSONObject finalObj = new JSONObject();
        try {
            User user = UserDAO.getUser(login, password);
            obj.put("id", user.id.toString());
            obj.put("firstName", user.firstName);
            obj.put("lastName", user.lastName);
            obj.put("login", user.login);
            obj.put("password", user.password);
            finalObj.put("success", obj);
        } catch (Exception e) {
            finalObj.put("failure", "Nie ma takiego użytkownika");
            return finalObj;
        }
        return finalObj;
    }

}
package com.sentinel.hibernate;

import com.sentinel.persistance.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.List;

public class UserDTO {

    public static JSONObject addUser(Integer id, String firstName, String lastName, String email, String password) {

        JSONObject obj = new JSONObject();
        Transaction tx = null;
        if (!isEmailInDatabase(email)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                User user = new User(id, firstName, lastName, email, password);
                session.save(user);
                tx.commit();
                obj.put("success", "Zarejestrowano pomyślnie, możesz się zalogować.");
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                obj.put("failure", "Nie udało się zarejestrować.");
            }
        } else {
            obj.put("failure", "Niepowodzenie, podany email już istnieje w bazie danych.");
        }
        return obj;
    }

    public static boolean isEmailInDatabase(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM User where email = '" + email + "'";
        List results = session.createQuery(hql)
                .list();
        session.close();
        return results.size() > 0;
    }

    public static User getUser(String email, String password) {
        User user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from User where email = '" + email + "' and password = '" + password + "'";
            List results = session.createQuery(hql)
                    .list();
            user = (User) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        return user;
    }

    public static JSONObject createJsonResponseFromRequest(String email, String password) {
        JSONObject obj = new JSONObject();
        JSONObject finalObj = new JSONObject();
        try {
            User user = UserDTO.getUser(email, password);
            obj.put("id", user.id.toString().trim());
            obj.put("firstName", user.firstName.trim());
            obj.put("lastName", user.lastName.trim());
            obj.put("email", user.email.trim());
            obj.put("password", user.password.trim());
            finalObj.put("success", obj);
        } catch (Exception e) {
            finalObj.put("failure", "Nie ma takiego użytkownika");
            return finalObj;
        }
        return finalObj;
    }

}
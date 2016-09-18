package com.sentinel.hibernate;

import com.sentinel.persistance.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.util.List;

public class UserDTO {

    public static void addUser(Integer id, String firstName, String lastName, String email, String password) throws HibernateException {

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = new User(id, firstName, lastName, email, password);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


    public static User checkUser(String email, String password) throws HibernateException {

        User user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User where email = '" + email + "' and password = '" + password + "'";
            List results = session.createQuery(hql)
                    .list();
            user = (User) results.get(0);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        return user;
    }

    public static JSONObject getUser(String email, String password) {
        JSONObject obj = new JSONObject();
        JSONObject finalObj = new JSONObject();

        try {
            User user = UserDTO.checkUser(email, password);
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
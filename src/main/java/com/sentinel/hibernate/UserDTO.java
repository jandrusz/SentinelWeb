package com.sentinel.hibernate;

import com.sentinel.persistance.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDTO {

    public static void addToDB() {

        UserDTO userDto = new UserDTO();

        userDto.addUser("1", "Ali", "Ali", "as@as", "haselko");
        userDto.addUser("2", "Das", "Ali", "as@as", "haselko");
        userDto.addUser("3", "Paul", "Ali", "as@as", "haselko");

    }

    public static void addUser(String id, String firstName, String lastName, String email, String password) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User employee = new User(id, firstName, lastName, email, password);

            session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static User getUser(String email, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User();
        try {
            String hql = "FROM User where email = '" + email + "' and password = '" + password + "'";

            List results = session.createQuery(hql)
                    .list();
            user = (User) results.get(0);
            System.out.println(user);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            session.close();
        }

        return user;
    }

}
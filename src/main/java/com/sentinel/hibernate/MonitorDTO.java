package com.sentinel.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class MonitorDTO {


    static Integer getNumberOfChildrenForUser(String idUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String numberOfChildren = "select count(*) from Monitor where idUser = '" + idUser + "')";
            List numberOfChildrenResult = session.createQuery(numberOfChildren)
                    .list();

            return Integer.valueOf(numberOfChildrenResult.get(0).toString());
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }


    //TODO
    static Integer bindChildToParent(Integer idUser, Integer idChild) {



        return null;
    }


}

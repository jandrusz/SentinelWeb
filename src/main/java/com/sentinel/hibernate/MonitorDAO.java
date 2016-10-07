package com.sentinel.hibernate;

import com.sentinel.model.Monitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import java.util.List;

public class MonitorDAO {


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

    static void bindChildToParent(Integer idUser, Integer idChild) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Monitor user = new Monitor(idUser, idChild);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
    }

    public static JSONObject unbindUserFromChild(String idUser, String idChild){
        JSONObject finalObj = new JSONObject();
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            String hql = "delete from Monitor where idUser = '" + idUser + "' and idChild = ' "+ idChild + "')";
            Query q = session.createQuery(hql);
            q.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }

        finalObj.put("success","Usunięto");
        return finalObj;
    }


}

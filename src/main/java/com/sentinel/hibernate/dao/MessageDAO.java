package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Message;
import com.sentinel.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import java.util.List;

public class MessageDAO {

    public static JSONObject getMessage(String idChild) {
        Message message;
        JSONObject finalObj = new JSONObject();
        JSONObject obj = new JSONObject();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String list = "from Message where idChild = '" + idChild + "' and read ='0')";
            List messages = session.createQuery(list).list();

            message = (Message) messages.get(0);

            obj.put("id", message.id.toString());
            obj.put("textFrom", message.textFrom);
            obj.put("message", message.message);
            obj.put("time", message.time);

            finalObj.put("success", obj);
            setReadToTrue(message.id.toString());

        } catch (HibernateException e) {
            finalObj.put("failure", "brak wiadomosci");
            return finalObj;
        }
        return finalObj;
    }

    private static void setReadToTrue(String id){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                String hql = "update Message set read = '1' where id = ' " + id + "')";
                Query q = session.createQuery(hql);
                q.executeUpdate();
                tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void saveMessage(String idChild, String userFirstName, String message, String time){
        Transaction tx = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                Message child = new Message(userFirstName,message,"0",Integer.parseInt(idChild), time);
                session.save(child);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
            }
    }

}

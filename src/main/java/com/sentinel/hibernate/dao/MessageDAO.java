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

            String textFrom = UserDAO.getUserFirstName(message.idUser);

            obj.put("id", message.idMessage.toString());
            obj.put("textFrom", textFrom);
            obj.put("message", message.message);
            obj.put("time", message.time);

            setReadToTrue(message.idMessage.toString());
            finalObj.put("success", obj);

        } catch (HibernateException | IndexOutOfBoundsException e) {
            finalObj.put("failure", "brak wiadomosci");
            return finalObj;
        }
        return finalObj;
    }

    private static void setReadToTrue(String id){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                String hql = "update Message set read = '1' where idMessage = ' " + id + "')";
                Query q = session.createQuery(hql);
                q.executeUpdate();
                tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void saveMessage(String idChild, String id, String message, String time){
        Transaction tx = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                Message child = new Message(Integer.parseInt(id),message,"0",Integer.parseInt(idChild), time);
                session.save(child);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
            }
    }

}

package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.Monitor;
import com.sentinel.hibernate.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

public class MonitorDAO {

	static Integer getNumberOfChildrenForUser(String idUser) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String numberOfChildren = "select count(*) from Monitor where idUser = '" + idUser + "')";
			List numberOfChildrenResult = session.createQuery(numberOfChildren).list();

			return Integer.valueOf(numberOfChildrenResult.get(0).toString());
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	//TODO nie wysyla failure bo wczesniej rzuca wyjatek
	public static JSONObject bindChildToParent(String login, String password, String idUser) {
		JSONObject finalObj = new JSONObject();

		try {
			if (ChildDAO.checkIfCredentialsAreCorrect(login, password)) {
				MonitorDAO.bindChildToParent(Integer.parseInt(idUser), ChildDAO.getChildId(login));
				finalObj.put("success", "Dodano dziecko");
			} else {
				finalObj.put("failure", "Nie udało się dodać dziecka");
			}
			return finalObj;
		} catch (Exception e) {
			finalObj.put("failure", "Nie udało się dodać dziecka");
			return finalObj;
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
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public static JSONObject unbindUserFromChild(String idUser, String idChild) {
		JSONObject finalObj = new JSONObject();
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String hql = "delete from Monitor where idUser = '" + idUser + "' and idChild = ' " + idChild + "')";
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
			removeIfDoesntHaveParent(idChild);

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			finalObj.put("failure", "Nie udało się usunąć");
			return finalObj;
		}
		finalObj.put("success", "Usunięto");
		return finalObj;
	}

	private static boolean hasParent(String idChild) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Monitor where idChild = '" + idChild + "'";
			List results = session.createQuery(hql).list();
			session.close();
			return results.size() > 0;
		} catch (Exception e) {
			return false;
		}

	}

	private static void removeIfDoesntHaveParent(String idChild) {

		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			if (!hasParent(idChild)) {
				tx = session.beginTransaction();
				String hql = "update Child set idSchedule = '0' where idChild = ' " + idChild + "')";
				Query q = session.createQuery(hql);
				q.executeUpdate();
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}

	}

}

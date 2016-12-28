package com.sentinel.hibernate.dao;

import com.sentinel.hibernate.model.User;
import com.sentinel.hibernate.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;

public class UserDAO {

	public static JSONObject addUser(String firstName, String lastName, String login, String password) {

		JSONObject obj = new JSONObject();
		Transaction tx = null;
		if (!isLoginInDatabase(login)) {
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				tx = session.beginTransaction();
				User user = new User(firstName, lastName, login, password);
				session.save(user);
				tx.commit();
				obj.put("success", "Zarejestrowano pomyślnie, możesz się zalogować");
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				obj.put("failure", "Nie udało się zarejestrować");
			}
		} else {
			obj.put("failure", "Niepowodzenie, podany login już istnieje w bazie danych");
		}
		return obj;
	}

	private static boolean isLoginInDatabase(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM User where login = '" + login + "'";
		List results = session.createQuery(hql).list();
		session.close();
		return results.size() > 0;
	}

	private static User getUser(String login, String password) {
		User user;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Criteria criteria = session.createCriteria(User.class);
			Criterion _login = Restrictions.eq("login", login);
			Criterion _password = Restrictions.eq("password", password);
			LogicalExpression andExp = Restrictions.and(_login, _password);

			criteria.add(andExp);
			List results = criteria.list();
			user = (User) results.get(0);
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
		return user;
	}

	public static boolean checkIfCredentialsAreCorrect(String login, String password) {
		if (getUser(login, password) != null) {
			return true;
		}
		return false;
	}

	public static JSONObject getUserData(String login, String password) {
		JSONObject obj = new JSONObject();
		JSONObject finalObj = new JSONObject();
		try {
			User user = getUser(login, password);
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
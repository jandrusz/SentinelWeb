package com.sentinel.hibernate.utils;

import com.sentinel.hibernate.model.Area;
import com.sentinel.hibernate.model.Child;
import com.sentinel.hibernate.model.Location;
import com.sentinel.hibernate.model.Monitor;
import com.sentinel.hibernate.model.Schedule;
import com.sentinel.hibernate.model.ScheduleEntry;
import com.sentinel.hibernate.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Child.class);
			configuration.addAnnotatedClass(Monitor.class);
			configuration.addAnnotatedClass(Schedule.class);
			configuration.addAnnotatedClass(ScheduleEntry.class);
			configuration.addAnnotatedClass(Location.class);
			configuration.addAnnotatedClass(Area.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}

}

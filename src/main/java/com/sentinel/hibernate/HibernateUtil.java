package com.sentinel.hibernate;

import com.sentinel.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


class HibernateUtil {

    private static SessionFactory sessionFactory;

    static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Child.class);
            configuration.addAnnotatedClass(Monitor.class);
            configuration.addAnnotatedClass(Schedule.class);
            configuration.addAnnotatedClass(ScheduleEntry.class);
            configuration.addAnnotatedClass(Location.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}

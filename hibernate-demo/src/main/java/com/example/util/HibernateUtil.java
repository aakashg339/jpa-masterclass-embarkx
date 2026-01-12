package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.model.UserInfo;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}

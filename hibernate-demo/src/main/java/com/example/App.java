package com.example;

import org.hibernate.Session;

import com.example.model.UserInfo;
import com.example.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSession();

        try {
            UserInfo user = new UserInfo("Alice", "Alice@email.com");

            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();

            System.out.println("User saved with ID: " + user.getId());
        } finally {
            HibernateUtil.close();
        }
    }
}

package com.example;

import com.example.model.UserInfo;
import com.example.util.JPAUtil;

import jakarta.persistence.EntityManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            UserInfo user = new UserInfo("Alice", "Alice@email.com");

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

            System.out.println("User saved with ID: " + user.getId());
        } finally {
            entityManager.close();
            JPAUtil.close();
        }
    }
}

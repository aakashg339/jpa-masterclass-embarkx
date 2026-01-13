package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.model.UserInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

}

@Component
class JPAService implements CommandLineRunner {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            UserInfo user = new UserInfo("Alice", "Alice@email.com");

            // entityManager.getTransaction().begin();
            entityManager.persist(user);
            // entityManager.getTransaction().commit();

            System.out.println("User saved with ID: " + user.getId());
        } finally {
            // entityManager.close();
        }
    }

}

package com.demo.healthcare;

import org.springframework.boot.CommandLineRunner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        
    }

}

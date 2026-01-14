package com.demo.healthcare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30);
        entityManager.persist(patient);
        // System.out.println("Persisted Patient: " + p);

        // Managed entity
        // patient.setName("John Doe Updated");
        // patient.setAge(95);
        // System.out.println("Persisted Patient: " + patient);
    
        // Detached
        entityManager.detach(patient);
        // patient.setName("John Doe Updated");
        // patient.setAge(95);

    }

}

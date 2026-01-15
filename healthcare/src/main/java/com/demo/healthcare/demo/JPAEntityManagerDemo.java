package com.demo.healthcare.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

// @Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Patient patient = new Patient("John Doe", 30);
        // entityManager.persist(patient);
        // // System.out.println("Persisted Patient: " + p);

        // // Managed entity
        // // patient.setName("John Doe Updated");
        // // patient.setAge(95);
        // // System.out.println("Persisted Patient: " + patient);

        // // check if managed by entity mamager
        // System.out.println("Is managed: " + entityManager.contains(patient));
    
        // // Detached
        // entityManager.detach(patient);

        // System.out.println("Is managed [After detached]: " + entityManager.contains(patient));
        // // patient.setName("John Doe Updated");
        // // patient.setAge(95);

        // // updating entity if detached. If not then not required.
        // entityManager.merge(patient);

        // // FINDING ENTITIES
        // Patient findPatient1L = entityManager.find(Patient.class, 1L);
        // System.out.println(findPatient1L);

        // // If both print (above and below) called after detach then first fetched from DB but second fetched from cache of persistence context
        // Patient findPatient1LAgain = entityManager.find(Patient.class, 1L);
        // System.out.println(findPatient1LAgain);

        // // getReference() - Lazy loading
        // Patient patientProxy = entityManager.getReference(Patient.class, 1L);
        // System.out.println("GOT PROXY");
        // System.out.println(patientProxy.getName());

        // // merge
        // Patient patient = new Patient("John Doe", 30);
        // Patient mergedEntity = entityManager.merge(patient);

        // // remove()
        // Patient patient = new Patient("John Doe", 30);
        // entityManager.persist(patient);
        // entityManager.remove(patient);

        // // clear() and flush()
        // Patient patient = new Patient("John Doe", 30);
        // entityManager.persist(patient);
        // patient.setName("Update 1");
        // // entityManager.flush();
        // patient.setAge(33);

        // entityManager.clear();
        // patient.setName("Update 2");

        // refresh()
        Patient patient = new Patient("John Doe", 30);
        entityManager.persist(patient);
        patient.setName("Update 1");
        entityManager.refresh(patient);
        patient.setAge(155);
    }

}

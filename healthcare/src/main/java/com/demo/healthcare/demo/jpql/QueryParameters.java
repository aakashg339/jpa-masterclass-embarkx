package com.demo.healthcare.demo.jpql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class QueryParameters {

    @Autowired
    private PatientRepository patientRepository;

    public void execute(EntityManager entityManager) {
        // // NAMED
        // TypedQuery<Patient> query = entityManager.createQuery(
        //     "SELECT p FROM Patient p WHERE p.name = :name", Patient.class
        // );
        // query.setParameter("name", "John Doe");
        // System.out.println("SIZE: " + query.getResultList().size());

        // // POSITIONAL
        // TypedQuery<Patient> positionalQuery = entityManager.createQuery(
        //     "SELECT p FROM Patient p WHERE p.name = ?1 AND p.gender = ?2", Patient.class
        // );
        // positionalQuery.setParameter(1, "John Doe");
        // positionalQuery.setParameter(2, Gender.MALE);
        // System.out.println("SIZE: " + positionalQuery.getResultList().size());

        // USING REPOSITORY
        List<Patient> patients = patientRepository.findByName("John Doe");
        System.out.println("SIZE: " + patients.size());

        List<Patient> patients2 = patientRepository.findByNameAndGender("John Doe", Gender.MALE);
        System.out.println("SIZE: " + patients2.size());

        System.out.println("Exists by email: " + patientRepository.existsByEmail("alice@gmail.com"));

        System.out.println("Count by age: " + patientRepository.countByAge(33));
    }

}

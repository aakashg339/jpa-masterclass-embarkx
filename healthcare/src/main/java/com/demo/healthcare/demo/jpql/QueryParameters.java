package com.demo.healthcare.demo.jpql;

import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class QueryParameters {

    public void execute(EntityManager entityManager) {
        // NAMED
        TypedQuery<Patient> query = entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.name = :name", Patient.class
        );
        query.setParameter("name", "John Doe");
        System.out.println("SIZE: " + query.getResultList().size());

        // POSITIONAL
        TypedQuery<Patient> positionalQuery = entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.name = ?1 AND p.gender = ?2", Patient.class
        );
        positionalQuery.setParameter(1, "John Doe");
        positionalQuery.setParameter(2, Gender.MALE);
        System.out.println("SIZE: " + positionalQuery.getResultList().size());
    }

}

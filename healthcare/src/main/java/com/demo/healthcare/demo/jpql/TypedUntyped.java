package com.demo.healthcare.demo.jpql;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PersonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

// @Component
public class TypedUntyped implements CommandLineRunner {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    public TypedUntyped(DoctorRepository doctorRepository, PatientRepository patientRepository,
            PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // TYPED
        TypedQuery<Patient> typedQuery = entityManager.createQuery(
            "SELECT p FROM Patient p", Patient.class
        );
        List<Patient> typedQueryPatients = typedQuery.getResultList();

        // UNTYPED
        Query untypedQuery = entityManager.createQuery(
            "SELECT p FROM Patient p"
        );
        List<Patient> untypedQueryPatients = untypedQuery.getResultList();

    }

}

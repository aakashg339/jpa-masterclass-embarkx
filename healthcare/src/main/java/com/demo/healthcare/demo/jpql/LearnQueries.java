package com.demo.healthcare.demo.jpql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;

import jakarta.persistence.EntityManager;

// @Component
public class LearnQueries {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public void execute(EntityManager entityManager) {
        List<Patient> older = patientRepository.findOlderThan(32);
        System.out.println("Total Patients: " + older.size());

        List<Patient> nameStartingWith = patientRepository.findByNameStartingWith("John%");
        nameStartingWith.forEach(
            p -> System.out.println("Name match: " + p.getName())
        );

        List<Patient> genderPatient = patientRepository.findByGenders(List.of(Gender.MALE, Gender.FEMALE));
        genderPatient.forEach(
            p -> System.out.println("Gender match: " + p.getName())
        );

        List<Patient> ageRange = patientRepository.findByAgeRange(32, 40);
        ageRange.forEach(
            p -> System.out.println("Age range match: " + p.getName())
        );

        List<Patient> unassignedPatients = patientRepository.findUnassignedPatients();
        unassignedPatients.forEach(
            p -> System.out.println("Doctor null match: " + p.getName())
        );

        // SORTING RESULTS
        List<Patient> sortBy = patientRepository.sortByAge();
        sortBy.forEach(
            p -> System.out.println("Sorted Data: " + p.getName())
        );

        List<Patient> sortAndFilter = patientRepository.findByGenderSortByAge(Gender.MALE);
        sortAndFilter.forEach(
            p -> System.out.println("Sorted and Filtered Data: " + p.getName())
        );

        // JOINS
        List<Patient> innerJoin = patientRepository.findPatientsWithDoctor("Cardiology");
        innerJoin.forEach(
            p -> System.out.println("Innner Join Data: " + p.getName())
        );

        List<Patient> leftJoin = patientRepository.findPatientsWithDoctorLeft();
        leftJoin.forEach(
            p -> System.out.println("Left Join Data: " + p.getName())
        );

        // As left join so below will not work and we will not have doctor data
        // leftJoin.forEach(
        //     p -> {
        //         System.out.println("Left Join Data: " + p.getName());
        //         System.out.println("DOCTOR NAME: " + p.getDoctor().getName());
        //     }
        // );

        List<Patient> joinFetch = patientRepository.findPatientsWithDoctorJoinFetch();
        joinFetch.forEach(
            p -> {
                System.out.println("joinFetch Data: " + p.getName());
                System.out.println("DOCTOR NAME: " + p.getDoctor().getName());
            }
        );

        // AGGREGATION
        System.out.println("AGGREGATED DATA - COUNT");
        List<Object[]> countQuery = doctorRepository.countOfPatientsBySpecialization("Cardiology");
        countQuery.forEach(
            row -> {
                System.out.println("SPEC: " + row[0] + " | Patient: " + row[1]);
            }
        );

        System.out.println("AGGREGATED DATA - AVG");
        List<Object[]> avgQuery = patientRepository.averageAgeByGender();
        avgQuery.forEach(
            row -> {
                Gender gender = (Gender) row[0];
                Double avgAge = (Double) row[1];
                System.out.println("Gender: " + gender + " | Age: " + avgAge);
            }
        );


        // BULK UPDATE / DELETE
        // int bulkUpdateCount = patientRepository.bulkIncreaseAge();
        // System.out.println("BULK UPDATED DONE FOR: " + bulkUpdateCount);

        // int bulkDeleteByAgeLessThanCount = patientRepository.bulkDeleteByAgeLessThan(35);
        // System.out.println("BULK DELETE DONE FOR: " + bulkDeleteByAgeLessThanCount);

        // SUMMARIES
        System.out.println("SUMMARIES");
        List<PatientSummary> summaries = patientRepository.getPatientSummary();
        summaries.forEach(System.out::println);


        // NAMED QUERIES
        System.out.println("NAMED QUERIES");
        
        // List<Patient> patientList = entityManager.createNamedQuery("Patient.findByNameStartingWith")
        //     .setParameter("prefix", "J%")
        //     .getResultList();

        // Same as above but using method with repository instead on defined at the top of entity
        List<Patient> patientList = patientRepository.findByNameStartingWith("J%");
        patientList.forEach(p -> System.out.println("Named query Data: " + p));
    }

}

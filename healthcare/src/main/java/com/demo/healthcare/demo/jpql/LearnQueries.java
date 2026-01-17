package com.demo.healthcare.demo.jpql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;

import jakarta.persistence.EntityManager;

@Component
public class LearnQueries {

    @Autowired
    private PatientRepository patientRepository;

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
    }

}

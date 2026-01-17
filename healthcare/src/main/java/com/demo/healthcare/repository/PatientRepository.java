package com.demo.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // @Query("SELECT p FROM Patient p WHERE p.name = :name")
    List<Patient> findByName(@Param("name") String name);

    // @Query("SELECT p FROM Patient p WHERE p.name = ?1 AND p.gender = ?2")
    List<Patient> findByNameAndGender(String name, Gender gender);

    boolean existsByEmail(String email);

    int countByAge(int age);

}

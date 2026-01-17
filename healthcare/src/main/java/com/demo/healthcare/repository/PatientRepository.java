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

    @Query("SELECT p FROM Patient p WHERE p.age > :minAge")
    List<Patient> findOlderThan(@Param("minAge") int minAge);

    @Query("SELECT p FROM Patient p WHERE p.name LIKE :prefix")
    List<Patient> findByNameStartingWith(@Param("prefix") String prefix);

    @Query("SELECT p FROM Patient p WHERE p.gender IN :genders")
    List<Patient> findByGenders(@Param("genders") List<Gender> genders);

    @Query("SELECT p FROM Patient p WHERE p.age BETWEEN :start AND :end")
    List<Patient> findByAgeRange(@Param("start") int start, @Param("end") int end);

    @Query("SELECT p FROM Patient p WHERE p.doctor IS NULL")
    List<Patient> findUnassignedPatients();

    // SORTING RESULTS
    @Query("SELECT p FROM Patient p ORDER BY p.age DESC, p.name ASC")
    List<Patient> sortByAge();

    @Query("SELECT p FROM Patient p WHERE p.gender = ?1 ORDER BY p.age DESC")
    List<Patient> findByGenderSortByAge(Gender gender);

}

package com.demo.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.healthcare.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d.specialization, COUNT(p) FROM Doctor d JOIN d.patients p WHERE d.specialization = ?1 GROUP BY d.specialization")
    List<Object[]> countOfPatientsBySpecialization(String specialization);

}

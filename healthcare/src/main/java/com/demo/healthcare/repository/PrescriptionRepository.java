package com.demo.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.healthcare.model.Prescription;
import com.demo.healthcare.model.PrescriptionId;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, PrescriptionId> {

}

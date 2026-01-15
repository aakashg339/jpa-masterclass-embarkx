package com.demo.healthcare.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;

// @Component
public class FetchTypeDemo implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public FetchTypeDemo(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== STEP 1: Insert Sample Data ===");

        Doctor doctor = new Doctor("Dr. Smith");

        Patient p1 = new Patient("John", 30);
        Patient p2 = new Patient("Alice", 25);
        p1.setDoctor(doctor);
        p2.setDoctor(doctor);

        MedicalRecord m1 = new MedicalRecord("Allergy to penicillin");
        MedicalRecord m2 = new MedicalRecord("History of asthma");

        p1.setMedicalRecord(m1);
        p2.setMedicalRecord(m2);

        patientRepository.saveAll(List.of(p1, p2));

        System.out.println("=== STEP 2: FetchType demo ===");
        patientRepository.findById(1L);
        doctorRepository.findById(1L);
    }

}

package com.demo.healthcare.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;

import jakarta.transaction.Transactional;

// @Component
public class JPARelationshipsDemo implements CommandLineRunner {

    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;
    
    public JPARelationshipsDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    // @Transactional
    public void run(String... args) throws Exception {
        // // ONE TO ONE
        // MedicalRecord medicalRecord = new MedicalRecord("Fever");
        // medicalRecordRepository.save(medicalRecord);
        
        // Patient patient = new Patient("John Doe", 30);
        // patient.setMedicalRecord(medicalRecord);
        // patientRepository.save(patient);

        // // Accessing data
        // System.out.println("Patient's Record: " + patient.getMedicalRecord().getDiagnosis());

        // MedicalRecord fetchedMedicalRecord = medicalRecordRepository
        //     .findById(medicalRecord.getId())
        //     .orElseThrow(() -> new RuntimeException("Medical record with id " + medicalRecord.getId() + " not present"));
        // System.out.println("Record's Patient: " + fetchedMedicalRecord.getPatient().getName());

        // MANY TO ONE and ONE TO MANY
        Doctor doctor1 = new Doctor("Dr. Alex");
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alene");
        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 33);
        patient2.setDoctor(doctor1);
        patientRepository.save(patient2);

        // doctor1.setPatients(List.of(patient1, patient2));
        
        System.out.println("Patient's docter: " + patient1.getDoctor().getName());
        // System.out.println("Docter's patient: " + doctor1.getPatients().get(0).getName());

        // Doctor fetchedDoctor1 = doctorRepository.findById(doctor1.getId())
        //     .orElseThrow(() -> new RuntimeException("Doctor not present with id: " + doctor1.getId()));

        // System.out.println("Docter's patient: " + fetchedDoctor1.getPatients().get(0).getName());

    }

}

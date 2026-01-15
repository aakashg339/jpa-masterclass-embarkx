package com.demo.healthcare.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;

@Component
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

    }

}

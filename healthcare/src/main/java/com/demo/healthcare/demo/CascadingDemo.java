package com.demo.healthcare.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;

// @Component
public class CascadingDemo implements CommandLineRunner {

    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;

    public CascadingDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository,
            DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        Doctor doctor1 = new Doctor("Dr. Alex");
        // doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alene");
        // doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
        // patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 33);
        patient2.setDoctor(doctor1);
        // patientRepository.save(patient2);

        patientRepository.saveAll(List.of(patient1, patient2));

        doctor1.setPatients(List.of(patient1, patient2));
        
        // CascadeType.REMOVE
        // Doctor doctor = doctorRepository.findById(1L).get();
        // doctorRepository.delete(doctor);

        // CacadeType.MERGE
        System.out.println("===CascadeType.REMOVE===");
        Doctor managedDoctor = doctorRepository.findById(1L)
            .orElseThrow();
        managedDoctor.setName("Dr. Updated");
        
        Patient managedPatient = patientRepository.findById(1L)
            .orElseThrow();
        managedPatient.setAge(44);

        managedDoctor.setPatients(List.of(managedPatient));

        doctorRepository.save(managedDoctor);
    }

}

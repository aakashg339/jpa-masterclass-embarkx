package com.demo.healthcare.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Component
public class WithoutTransactions implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public WithoutTransactions(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30, "john@example.com", Gender.MALE);
        patientRepository.save(patient);

        // Unchecked exception (Runtime exception or Error) - @Transactional will not execute insert query for Patient also.
        // if(true) {
        //     throw new RuntimeException("Simulated down after saving patient");
        // }

        // Checked exception - Patient will be inserted. To rollback on this too, use the values of @Transactional annotation
        if(true) {
            throw new Exception("Simulated down after saving patient");
        }


        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis("Hypertension");
        record.setPatient(patient);
        medicalRecordRepository.save(record);

        patient.setMedicalRecord(record);
        patientRepository.save(patient);
    }

}

package com.demo.healthcare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;

// @Component
public class JPARepositoryDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public JPARepositoryDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30);
        patientRepository.save(patient);
    }

}

package com.demo.healthcare.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;

// @Component
public class OptimisticLockingDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public OptimisticLockingDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");
        
        Patient alice = new Patient("Alice Johnson", 30);
        alice.setGender(Gender.FEMALE);
        alice.setDoctor(drSmith);

        patientRepository.save(alice);

        Patient p1 = patientRepository.findById(1L).get();
        Patient p2 = patientRepository.findById(1L).get();
        // p1.setName("New Alice 1");
        patientRepository.save(p1);

        // Error due to optimistic locking
        // p2.setName("New Alice 2");
        patientRepository.save(p2);
    }

}

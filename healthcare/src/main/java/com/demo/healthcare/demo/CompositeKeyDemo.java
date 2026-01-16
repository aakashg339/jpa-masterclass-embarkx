package com.demo.healthcare.demo;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Medicine;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.model.Prescription;
import com.demo.healthcare.model.PrescriptionId;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicineRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PrescriptionRepository;

@Component
public class CompositeKeyDemo implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    
    private final PatientRepository patientRepository;
    
    private final PrescriptionRepository prescriptionRepository;
    
    private final MedicineRepository medicineRepository;
    
    public CompositeKeyDemo(DoctorRepository doctorRepository, PatientRepository patientRepository,
            PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");

        Patient alice = new Patient("Alice Johnson", 30);
        alice.setDoctor(drSmith);
        alice.setGender(Gender.FEMALE);
        // byte[] imageData = Files.readAllBytes(new ClassPathResource("/images/person-icon-white-icon.png").getFile().toPath());
        // alice.setProfilePicture(imageData);
        patientRepository.save(alice);

        MedicalRecord aliceRecord = new MedicalRecord("Alergic to penicillin");
        alice.setMedicalRecord(aliceRecord);

        patientRepository.save(alice);

        Medicine m1 = new Medicine("Paracetamol");
        Medicine m2 = new Medicine("Ibuprofen");
        Medicine m3 = new Medicine("Amoxicillin");

        medicineRepository.saveAll(List.of(m1, m2, m3));

        Prescription prescription = new Prescription(
            drSmith,
            alice,
            List.of(m1, m2)
        );
        prescription.setNotes("Test 1");

        prescriptionRepository.save(prescription);

        // FETCHING PRESCRIPTION WITH COMPOSITE KEY
        PrescriptionId key = new PrescriptionId(
            drSmith.getId(),
            alice.getId()
        );

        Optional<Prescription> fetchedPrescription = prescriptionRepository.findById(key);
        if(fetchedPrescription.isPresent()) {
            Prescription p = fetchedPrescription.get();
            System.out.println("Found prescription for Dr. " + p.getDoctorId().getName()
                + " -> Patient: " + p.getPatientId().getName());
            System.out.println("Medicine Prescribed");
            p.getMedicines().forEach((medicine) -> System.out.println(" . " + medicine.getName()));
        }
    }

}

package com.demo.healthcare.model;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
// @IdClass(PrescriptionId.class)
public class Prescription {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    @EmbeddedId
    private PrescriptionId id;

    // @Id
    @MapsId("doctorId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctorId;

    // @Id
    @MapsId("patientId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "prescription_medicine",
        joinColumns = {
            @JoinColumn(name = "prescription_doctor_id", referencedColumnName = "doctor_id"),
            @JoinColumn(name = "prescription_patient_id", referencedColumnName = "patient_id")
        },
        inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    private String notes;

    public Prescription() {
        
    }

    public Prescription(Doctor doctorId, Patient patientId, List<Medicine> medicines) {
        this.id = new PrescriptionId(doctorId.getId(), patientId.getId());
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicines = medicines;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

}

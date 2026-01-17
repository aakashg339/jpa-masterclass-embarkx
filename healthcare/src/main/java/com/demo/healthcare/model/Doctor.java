package com.demo.healthcare.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor extends Person {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    // private String name;

    private String specialization;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Doctor() {
    }

    public Doctor(String name) {
        // this.name = name;
    }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public List<Patient> getPatients() {
        return patients;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}

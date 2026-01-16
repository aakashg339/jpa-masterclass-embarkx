package com.demo.healthcare.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "patient_name")
    private String name;
    
    private Integer age;

    @Embedded
    private Address address;

    @Transient
    private String ageGroup;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // @Lob
    // private byte[] profilePicture;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Patient() {
    }

    public Patient(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.ageGroup = calculateAgeGroup();
    }

    public Patient(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ageGroup = calculateAgeGroup();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        this.ageGroup = calculateAgeGroup();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private String calculateAgeGroup() {
        if(this.age <= 12)
            return "child";
        else if(this.age < 19)
            return "teen";
        else if(this.age < 60)
            return "adult";
        else
            return "old";
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // public byte[] getProfilePicture() {
    //     return profilePicture;
    // }

    // public void setProfilePicture(byte[] profilePicture) {
    //     this.profilePicture = profilePicture;
    // }

}

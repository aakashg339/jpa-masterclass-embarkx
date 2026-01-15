package com.project.app.entity;

import jakarta.persistence.*;

@Entity
public class StudentProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String major;

    // TODO: Bidirectional One-to-One with Student
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentProfile() {}
    
    public StudentProfile(String major) { this.major = major; }

    public Long getId() { return id; }
    
    public String getMajor() { return major; }
    
    public void setMajor(String major) { this.major = major; }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

}

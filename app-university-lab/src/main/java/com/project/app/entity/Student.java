package com.project.app.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    // TODO: One-to-One with StudentProfile
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentProfile profile;

    // TODO: One-to-Many with Enrollment
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    public Student() {}
    
    public Student(String name) { this.name = name; }

    public Long getId() { return id; }
    
    public String getName() { return name; }
    
    public void setName(String name) { this.name = name; }
    
    public StudentProfile getProfile() {
        return profile;
    }
    
    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }
    
    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

}

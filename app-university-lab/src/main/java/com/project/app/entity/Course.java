package com.project.app.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    // TODO: Many-to-One with Instructor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // TODO: Many-to-Many with Students via Enrollment
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    public Course() {}
    
    public Course(String title) { this.title = title; }

    public Long getId() { return id; }
    
    public String getTitle() { return title; }
    
    public void setTitle(String title) { this.title = title; }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

}

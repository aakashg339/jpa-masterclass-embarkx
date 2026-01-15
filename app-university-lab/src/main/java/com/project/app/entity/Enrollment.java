package com.project.app.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id = new EnrollmentId();

    // TODO: Map Many-to-One relationships with Student and Course
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    public Enrollment() {}

    public Enrollment(EnrollmentId id) { this.id = id; }

    public EnrollmentId getId() { return id; }
    
    public void setId(EnrollmentId id) { this.id = id; }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}


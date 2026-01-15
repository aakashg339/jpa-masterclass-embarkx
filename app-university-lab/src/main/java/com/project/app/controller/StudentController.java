package com.project.app.controller;

import com.project.app.entity.Student;
import com.project.app.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return repo.findAll();
    }
}

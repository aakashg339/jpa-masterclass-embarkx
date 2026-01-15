package com.project.app.repository;

import com.project.app.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}

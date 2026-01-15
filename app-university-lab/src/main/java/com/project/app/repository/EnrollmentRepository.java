package com.project.app.repository;

import com.project.app.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {}

package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

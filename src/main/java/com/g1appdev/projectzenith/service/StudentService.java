package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Student;
import com.g1appdev.projectzenith.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        logger.info("Creating new student: {} {}", student.getFirstName(), student.getLastName());
        try {
            Student savedStudent = studentRepository.save(student);
            logger.info("Student created successfully with id: {}", savedStudent.getId());
            return savedStudent;
        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage());
            throw new RuntimeException("Failed to create student", e);
        }
    }

    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        try {
            List<Student> students = studentRepository.findAll();
            logger.info("Retrieved {} students", students.size());
            for (Student student : students) {
                logger.debug("Student: id={}, firstName={}, lastName={}, email={}, course={}, year={}", 
                             student.getId(), student.getFirstName(), student.getLastName(), 
                             student.getEmail(), student.getCourse(), student.getYear());
            }
            return students;
        } catch (Exception e) {
            logger.error("Error fetching all students: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch students", e);
        }
    }
}

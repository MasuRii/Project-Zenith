package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Student;
import com.g1appdev.projectzenith.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    
    public Student updateStudent(Long id, Student studentDetails) {
        logger.info("Updating student with id: {}", id);
        try {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                student.setFirstName(studentDetails.getFirstName());
                student.setLastName(studentDetails.getLastName());
                student.setEmail(studentDetails.getEmail());
                student.setCourse(studentDetails.getCourse());
                student.setYear(studentDetails.getYear());

                Student updatedStudent = studentRepository.save(student);
                logger.info("Student updated successfully: id={}", updatedStudent.getId());
                return updatedStudent;
            } else {
                logger.warn("Student with id {} not found", id);
                throw new RuntimeException("Student not found");
            }
        } catch (Exception e) {
            logger.error("Error updating student: {}", e.getMessage());
            throw new RuntimeException("Failed to update student", e);
        }
    }

    
    public void deleteStudent(Long id) {
        logger.info("Deleting student with id: {}", id);
        try {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (studentOptional.isPresent()) {
                studentRepository.deleteById(id);
                logger.info("Student deleted successfully: id={}", id);
            } else {
                logger.warn("Student with id {} not found", id);
                throw new RuntimeException("Student not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage());
            throw new RuntimeException("Failed to delete student", e);
        }
    }
}

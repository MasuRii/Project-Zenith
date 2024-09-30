package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.StudentTeam;
import com.g1appdev.projectzenith.entity.StudentTeamId;
import com.g1appdev.projectzenith.repository.StudentTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTeamService {

    @Autowired
    private StudentTeamRepository studentTeamRepository;

    public StudentTeam createStudentTeam(StudentTeam studentTeam) {
        return studentTeamRepository.save(studentTeam);
    }

    public List<StudentTeam> getAllStudentTeams() {
        return studentTeamRepository.findAll();
    }

    public void deleteStudentTeam(Long studentId, Long teamId) {
        StudentTeamId studentTeamId = new StudentTeamId(studentId, teamId);
        studentTeamRepository.deleteById(studentTeamId);
    }
}

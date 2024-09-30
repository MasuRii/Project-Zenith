package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.StudentTeam;
import com.g1appdev.projectzenith.service.StudentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentTeams")
public class StudentTeamController {
    @Autowired
    private StudentTeamService studentTeamService;

    @PostMapping
    public ResponseEntity<StudentTeam> createStudentTeam(@RequestBody StudentTeam studentTeam) {
        StudentTeam createdStudentTeam = studentTeamService.createStudentTeam(studentTeam);
        return new ResponseEntity<>(createdStudentTeam, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentTeam>> getAllStudentTeams() {
        List<StudentTeam> studentTeams = studentTeamService.getAllStudentTeams();
        return new ResponseEntity<>(studentTeams, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/{teamId}")
    public ResponseEntity<Void> deleteStudentTeam(@PathVariable Long studentId, @PathVariable Long teamId) {
        studentTeamService.deleteStudentTeam(studentId, teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired(required = true)
    private ProjectService projectService;

    // Create Project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    // Get All Projects
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Get Project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        Optional<Project> projectOpt = projectService.getProjectById(id);
        return projectOpt.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // Update Project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project projectDetails) {
        Optional<Project> projectOpt = projectService.getProjectById(id);
        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setStartDate(projectDetails.getStartDate());
            project.setEndDate(projectDetails.getEndDate());
            project.setTeacherId(projectDetails.getTeacherId());
            project.setStatus(projectDetails.getStatus());
            project.setDeadline(projectDetails.getDeadline());
            Project updatedProject = projectService.updateProject(project);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
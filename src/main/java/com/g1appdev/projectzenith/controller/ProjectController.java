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
        try {
            projectDetails.setProject_id(id);
            Project updatedProject = projectService.updateProject(projectDetails);
            return ResponseEntity.ok(updatedProject);
        } catch (Exception e) {
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
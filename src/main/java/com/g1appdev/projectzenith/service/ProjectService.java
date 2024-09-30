package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired(required = true)
    private ProjectRepository projectRepository;

    // Create Project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Get All Projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get Project by ID
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    // Update Project
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    // Delete Project
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    // Additional business logic methods can be added here
}
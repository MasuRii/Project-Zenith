package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    // ... other service methods (e.g., getProjectById, updateProject, deleteProject)
}
package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        logger.info("Creating new project: {}", project.getName());
        try {
            Project savedProject = projectRepository.save(project);
            logger.info("Project created successfully with id: {}", savedProject.getId());
            return savedProject;
        } catch (Exception e) {
            logger.error("Error creating project: {}", e.getMessage());
            throw new RuntimeException("Failed to create project", e);
        }
    }

    public List<Project> getAllProjects() {
        logger.info("Fetching all projects");
        try {
            List<Project> projects = projectRepository.findAll();
            logger.info("Retrieved {} projects", projects.size());
            for (Project project : projects) {
                logger.debug("Project: id={}, name={}, description={}", 
                             project.getId(), project.getName(), project.getDescription());
            }
            return projects;
        } catch (Exception e) {
            logger.error("Error fetching all projects: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch projects", e);
        }
    }
}
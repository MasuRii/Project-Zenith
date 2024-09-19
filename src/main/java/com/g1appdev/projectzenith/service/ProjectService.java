package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create project", e);
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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch projects", e);
        }
    }

    

    public Project updateProject(Long id, Project projectDetails) {
        logger.info("Updating project with id: {}", id);
        try {
            Optional<Project> optionalProject = projectRepository.findById(id);
            if (!optionalProject.isPresent()) {
                logger.error("Project not found with id: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id: " + id);
            }
            Project existingProject = optionalProject.get();
            existingProject.setName(projectDetails.getName());
            existingProject.setDescription(projectDetails.getDescription());
            Project updatedProject = projectRepository.save(existingProject);
            logger.info("Project updated successfully with id: {}", updatedProject.getId());
            return updatedProject;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error updating project with id {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update project with id " + id, e);
        }
    }

    public void deleteProject(Long id) {
        logger.info("Deleting project with id: {}", id);
        try {
            if (!projectRepository.existsById(id)) {
                logger.error("Project not found with id: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id: " + id);
            }
            projectRepository.deleteById(id);
            logger.info("Project deleted successfully with id: {}", id);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting project with id {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete project with id " + id, e);
        }
    }
}
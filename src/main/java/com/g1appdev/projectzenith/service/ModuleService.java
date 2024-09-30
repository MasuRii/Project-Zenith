package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Module;
import com.g1appdev.projectzenith.entity.Project;
import com.g1appdev.projectzenith.repository.ModuleRepository;
import com.g1appdev.projectzenith.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Create Module
    public Module createModule(Module module) {
        Integer projectId = module.getProject().getProject_id();
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isPresent()) {
            module.setProject(projectOpt.get());
            return moduleRepository.save(module);
        } else {
            throw new RuntimeException("Project with ID " + projectId + " not found.");
        }
    }

    // Get All Modules
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    // Get Module by ID
    public Optional<Module> getModuleById(Integer id) {
        return moduleRepository.findById(id);
    }

    // Update Module
    public Module updateModule(Integer id, Module moduleDetails) {
        Module module = moduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Module with ID " + id + " not found."));

        module.setName(moduleDetails.getName());
        module.setDescription(moduleDetails.getDescription());
        module.setStatus(moduleDetails.getStatus());

        if (moduleDetails.getProject() != null) {
            Integer newProjectId = moduleDetails.getProject().getProject_id();
            Project newProject = projectRepository.findById(newProjectId)
                .orElseThrow(() -> new RuntimeException("Project with ID " + newProjectId + " not found."));
            module.setProject(newProject);
        }

        return moduleRepository.save(module);
    }

    // Delete Module
    public void deleteModule(Integer id) {
        Module module = moduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Module with ID " + id + " not found."));
        moduleRepository.delete(module);
    }
}
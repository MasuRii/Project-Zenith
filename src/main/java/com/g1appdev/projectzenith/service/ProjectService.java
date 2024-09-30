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

  public Project createProject(Project project) {
    return projectRepository.save(project);
  }

  public List < Project > getAllProjects() {
    return projectRepository.findAll();
  }

  public Optional < Project > getProjectById(Integer id) {
    return projectRepository.findById(id);
  }

  public Project updateProject(Project project) {
    return projectRepository.save(project);
  }

  public void deleteProject(Integer id) {
    projectRepository.deleteById(id);
  }

}
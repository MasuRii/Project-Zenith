package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Custom query methods can be added here, for example:
    List<Project> findByStatus(Project.Status status);
}
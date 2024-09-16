package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
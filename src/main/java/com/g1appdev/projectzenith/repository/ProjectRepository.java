package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // You can add custom query methods here if needed (e.g., findByTitle)
}
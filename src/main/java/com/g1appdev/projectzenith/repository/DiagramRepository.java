// DiagramRepository.java
package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Diagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagramRepository extends JpaRepository<Diagram, Integer> {
}
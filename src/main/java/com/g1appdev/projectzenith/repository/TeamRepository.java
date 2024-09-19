package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

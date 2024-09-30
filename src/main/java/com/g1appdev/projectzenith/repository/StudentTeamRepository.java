package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.StudentTeam;
import com.g1appdev.projectzenith.entity.StudentTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeamRepository extends JpaRepository<StudentTeam, StudentTeamId> {
}

package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository < Module, Integer > {

  List < Module > findByProject_ProjectId(Integer projectId);

  List < Module > findByStatus(Module.Status status);
}
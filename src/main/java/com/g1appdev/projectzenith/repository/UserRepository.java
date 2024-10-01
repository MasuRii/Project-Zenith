package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
}

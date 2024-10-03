// CommentRepository.java
package com.g1appdev.projectzenith.repository;

import com.g1appdev.projectzenith.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
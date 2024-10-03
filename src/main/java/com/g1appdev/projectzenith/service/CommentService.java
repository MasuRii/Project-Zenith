// CommentService.java
package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Comment;
import com.g1appdev.projectzenith.repository.CommentRepository;
import com.g1appdev.projectzenith.repository.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment getCommentById(Integer id){
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer id, Comment updatedComment){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setComment_text(updatedComment.getComment_text());
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }

    public void deleteComment(Integer id){
        commentRepository.deleteById(id);
    }
}
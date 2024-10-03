// CommentController.java
package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.Comment;
import com.g1appdev.projectzenith.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id){
        return commentService.getCommentById(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment){
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
    }
}
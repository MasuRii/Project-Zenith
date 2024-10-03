// Comment.java
package com.g1appdev.projectzenith.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @ManyToOne
    @JoinColumn(name = "diagram_id")
    private Diagram diagram;

    @Column(name = "comment_text", nullable = false, columnDefinition = "TEXT")
    private String comment_text;

    @Column(name = "comment_date")
    private Timestamp comment_date;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parent_comment;

    @OneToMany(mappedBy = "parent_comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies;

    public Comment() {
        this.comment_date = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters

    public Integer getComment_id(){
        return comment_id;
    }

    public void setComment_id(Integer comment_id){
        this.comment_id = comment_id;
    }

    public Diagram getDiagram(){
        return diagram;
    }

    public void setDiagram(Diagram diagram){
        this.diagram = diagram;
    }

    public String getComment_text(){
        return comment_text;
    }

    public void setComment_text(String comment_text){
        this.comment_text = comment_text;
    }

    public Timestamp getComment_date(){
        return comment_date;
    }

    public void setComment_date(Timestamp comment_date){
        this.comment_date = comment_date;
    }

    public Comment getParent_comment(){
        return parent_comment;
    }

    public void setParent_comment(Comment parent_comment){
        this.parent_comment = parent_comment;
    }

    public List<Comment> getReplies(){
        return replies;
    }

    public void setReplies(List<Comment> replies){
        this.replies = replies;
    }
}
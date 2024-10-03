// Diagram.java
package com.g1appdev.projectzenith.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "diagram")
public class Diagram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diagram_id;

    @Column(length = 50)
    private String type;

    @Column(name = "file_path", nullable = false, length = 255)
    private String file_path;

    @Column(name = "upload_date")
    private Timestamp upload_date;

    @Column(nullable = false)
    private Integer version = 1;

    @OneToMany(mappedBy = "diagram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Diagram() {
        this.upload_date = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters

    public Integer getDiagram_id() {
        return diagram_id;
    }

    public void setDiagram_id(Integer diagram_id) {
        this.diagram_id = diagram_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getFile_path(){
        return file_path;
    }

    public void setFile_path(String file_path){
        this.file_path = file_path;
    }

    public Timestamp getUpload_date(){
        return upload_date;
    }

    public void setUpload_date(Timestamp upload_date){
        this.upload_date = upload_date;
    }

    public Integer getVersion(){
        return version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
    }
}
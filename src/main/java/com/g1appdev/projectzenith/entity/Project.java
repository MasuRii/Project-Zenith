package com.g1appdev.projectzenith.entity;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    // ... other project attributes (e.g., startDate, endDate, teamMembers, etc.)

    // Getters and setters for all attributes

    // Constructors (if needed)

    // toString() method (optional, but helpful for debugging)
}

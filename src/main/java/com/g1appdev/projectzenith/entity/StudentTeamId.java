package com.g1appdev.projectzenith.entity;

import java.io.Serializable;
import java.util.Objects;

public class StudentTeamId implements Serializable {
    private Long student;
    private Long team;

    // Constructors, equals, hashCode
    public StudentTeamId() {}

    public StudentTeamId(Long student, Long team) {
        this.student = student;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTeamId that = (StudentTeamId) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, team);
    }
}

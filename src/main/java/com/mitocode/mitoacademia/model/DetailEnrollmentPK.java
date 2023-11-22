package com.mitocode.mitoacademia.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class DetailEnrollmentPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_enrollment", nullable = false, foreignKey = @ForeignKey(name = "ENROLLMENT_FK"))
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "COURSE_FK"))
    private Course course;
}

package com.mitocode.mitoacademia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(DetailEnrollmentPK.class)
public class DetailEnrollment {

    @Id
    private Enrollment enrollment;

    @Id
    private Course course;

    @Column(length = 30, nullable = false)
    private String classroom;
}

package com.mitocode.mitoacademia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(EnrollmentDetailPK.class)
public class EnrollmentDetail {

	
    @Id
    @EqualsAndHashCode.Include
    private Enrollment enrollment;

    @Id
    @EqualsAndHashCode.Include
    private Course course;

    @Column(length = 30, nullable = false)
    private String classroom;
}

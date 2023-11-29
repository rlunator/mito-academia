package com.mitocode.mitoacademia.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailEnrollmentDTO {

    @JsonBackReference
    private EnrollmentDTO enrollment;
    
    @NotNull
    private CourseDTO course;

    @NotNull
    private String classroom;
}

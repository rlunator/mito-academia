package com.mitocode.mitoacademia.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    private CourseDTO course;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String classroom;
}

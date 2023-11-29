package com.mitocode.mitoacademia.dto;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer idStudent;

    @NotNull
    @NotEmpty
    private String nameStudent;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String DNI;
    
    @NotNull
    private LocalDate birthDate;
    
    private Integer age;
    
    public void calculateAge(LocalDate birthDate) {
    	this.age = Period.between(birthDate, LocalDate.now()).getYears();
    }
}

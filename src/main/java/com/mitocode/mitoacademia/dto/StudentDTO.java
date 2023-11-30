package com.mitocode.mitoacademia.dto;

import java.time.LocalDate;
import java.time.Period;

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
public class StudentDTO {

    private Integer idStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String nameStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 18)
    private String DNI;
    
    @NotNull
    private LocalDate birthDate;
    
    private Integer age;
    
    public void calculateAge(LocalDate birthDate) {
    	this.age = Period.between(birthDate, LocalDate.now()).getYears();
    }
}

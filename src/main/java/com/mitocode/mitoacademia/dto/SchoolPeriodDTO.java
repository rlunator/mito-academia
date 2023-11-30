package com.mitocode.mitoacademia.dto;

import java.time.LocalDate;


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
public class SchoolPeriodDTO {
	
	private Integer IdSchoolPeriod;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 30)
	private String nameSchoolPeriod;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;

}

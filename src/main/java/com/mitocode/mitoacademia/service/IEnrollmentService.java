package com.mitocode.mitoacademia.service;

import java.util.List;

import com.mitocode.mitoacademia.dto.EnrollmentCuorseDTO;
import com.mitocode.mitoacademia.model.Enrollment;


public interface IEnrollmentService extends ICRUDService<Enrollment, Integer> {
	
	public List<EnrollmentCuorseDTO> getResumeEnrollment() throws Exception;

}

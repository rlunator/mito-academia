package com.mitocode.mitoacademia.service;

import java.util.List;

import com.mitocode.mitoacademia.dto.StudentDTO;
import com.mitocode.mitoacademia.model.Student;

public interface IStudentService extends ICRUDService<Student,Integer> {

	public List<StudentDTO> getStudentOrderAge()  throws Exception;
}

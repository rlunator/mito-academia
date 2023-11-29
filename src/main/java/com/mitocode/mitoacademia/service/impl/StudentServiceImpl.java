package com.mitocode.mitoacademia.service.impl;

import com.mitocode.mitoacademia.dto.StudentDTO;
import com.mitocode.mitoacademia.model.Student;
import com.mitocode.mitoacademia.repo.IGeneRepo;
import com.mitocode.mitoacademia.repo.IStudentRepo;
import com.mitocode.mitoacademia.service.IStudentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.util.Comparator.comparingInt;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDServiceImpl<Student,Integer> implements IStudentService{

    private final IStudentRepo repo;
    
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    
    private StudentDTO convertToDto(Student obj){
        return mapper.map(obj, StudentDTO.class);
    }
    
    @Override
    protected IGeneRepo<Student, Integer> getRepo() {
        return repo;
    }

	@Override
	public List<StudentDTO> getStudentOrderAge() throws Exception {
		return repo.findAll().stream()
							.map(e->{
									StudentDTO student = convertToDto(e);
									student.calculateAge(student.getBirthDate());
									return student;
								})
							.sorted(comparingInt(StudentDTO::getAge).reversed()).toList();
	}
}

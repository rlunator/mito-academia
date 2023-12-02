package com.mitocode.mitoacademia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mitocode.mitoacademia.model.Course;
import com.mitocode.mitoacademia.repo.ICourseRepo;
import com.mitocode.mitoacademia.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
	
	@MockBean
	private CourseServiceImpl service;
	
	@MockBean
	private ICourseRepo repo;
	
	private Course COURSE_1;
	private Course COURSE_2;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.service = new CourseServiceImpl(repo);
		
		COURSE_1 = new Course(1, "Programacion", "PRG01", true);
		COURSE_2 = new Course(2, "Matematicas", "MAT01", false);
		
		Mockito.when(repo.save(any())).thenReturn(COURSE_1);
		
	}
	
	@Test
	void readAllTest() throws Exception{
		List<Course> lisRepo = List.of(COURSE_1,COURSE_2);
		Mockito.when(repo.findAll()).thenReturn(lisRepo);
		List<Course> response = service.readAll();
		assertNotNull(response);
		assertEquals(response.size(), 2);
		
	}
	
	@Test
	void readByIdTest() throws Exception{
		Integer ID = 1;
		Mockito.when(repo.findById(any())).thenReturn(Optional.of(COURSE_1));
		Course response = service.readById(ID);
		assertNotNull(response);	
	}
	
	@Test
	void saveTest() throws Exception{
		Course response = service.save(COURSE_1);
		assertNotNull(response);	
	}
}

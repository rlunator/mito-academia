package com.mitocode.mitoacademia.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitocode.mitoacademia.model.Course;
import com.mitocode.mitoacademia.service.ICourseService;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ICourseService service;
	
	@MockBean(name = "courseMapper")
	private ModelMapper mapper;
	
	@Test
	void readAllTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
							.get("/course")
							.content(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk());
		
	}
	
	@Test
	void readByIdTest() throws Exception {
		final int ID = 1;
		Mockito.when(service.readById(anyInt())).thenReturn(mock(Course.class));
		mockMvc.perform(MockMvcRequestBuilders
							.get("/course/"+ID)
							.content(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk());
		
	}
}

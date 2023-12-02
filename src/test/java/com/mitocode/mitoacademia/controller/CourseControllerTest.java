package com.mitocode.mitoacademia.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.mitoacademia.dto.CourseDTO;
import com.mitocode.mitoacademia.exception.ModelNotFoundException;
import com.mitocode.mitoacademia.model.Course;
import com.mitocode.mitoacademia.service.ICourseService;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ICourseService service;
	
	@MockBean(name = "courseMapper")
	private ModelMapper mapper;
	
	Course COURSE_1 = new Course(1, "Programacion", "PRG01", true);
	Course COURSE_2 = new Course(2, "Matematicas", "MAT01", false);
	
	CourseDTO COURSEDTO_1 = new CourseDTO(1, "Programacion", "PRG01", true);
	CourseDTO COURSEDTO_2 = new CourseDTO(2, "Matematicas", "MAT01", false);
	
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
	
	@Test
	void createTest() throws Exception {
		
		Mockito.when(service.save(any())).thenReturn(COURSE_1);
		Mockito.when(mapper.map(COURSE_1, CourseDTO.class)).thenReturn(COURSEDTO_1);
		
		MockHttpServletRequestBuilder mockRequest  = MockMvcRequestBuilders
				.post("/course")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(COURSEDTO_1));
		
		mockMvc.perform(mockRequest)
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.nameOfCourse", is("Programacion")));
		
	}
	
	@Test
	void updateTest() throws Exception {
		int ID = 2;
		Mockito.when(service.update(any(), anyInt())).thenReturn(COURSE_2);
		Mockito.when(mapper.map(COURSE_2, CourseDTO.class)).thenReturn(COURSEDTO_2);
		
		MockHttpServletRequestBuilder mockRequest  = MockMvcRequestBuilders
				.put("/course/"+ID)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(COURSEDTO_2));
		
		mockMvc.perform(mockRequest)
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.acronym", is("MAT01")))
					.andExpect(jsonPath("$.state", is(false)));
		
	}
	
	@Test
	void updateErrorTest() throws Exception {
		int ID = 99;
		Mockito.when(service.update(any(), anyInt())).thenThrow(new ModelNotFoundException("ID NOT FOUND "+ID));
		
		MockHttpServletRequestBuilder mockRequest  = MockMvcRequestBuilders
				.put("/course/"+ID)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(COURSEDTO_2));
		
		mockMvc.perform(mockRequest)
					.andExpect(status().isNotFound())
					.andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
		
	}
	
	@Test
	void deleteTest() throws Exception {
		int ID = 1;
		Mockito.when(service.update(any(), anyInt())).thenThrow(new ModelNotFoundException("ID NOT FOUND "+ID));
		
		MockHttpServletRequestBuilder mockRequest  = MockMvcRequestBuilders
				.delete("/course/"+ID)
				.content(MediaType.APPLICATION_JSON_VALUE);
		
		mockMvc.perform(mockRequest)
						.andExpect(status().isNoContent());
		
	}
	
	@Test
	void deleteErrorTest() throws Exception {
		int ID = 99;
		Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND "+ ID)).when(service).delete(ID);
		
		MockHttpServletRequestBuilder mockRequest  = MockMvcRequestBuilders
				.delete("/course/"+ID)
				.content(MediaType.APPLICATION_JSON_VALUE);
		
		mockMvc.perform(mockRequest)
				.andExpect(status().isNotFound())
				.andExpect(resul -> assertTrue(resul.getResolvedException() instanceof ModelNotFoundException));
		
	}
}

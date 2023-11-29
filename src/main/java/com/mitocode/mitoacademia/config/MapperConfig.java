package com.mitocode.mitoacademia.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mitocode.mitoacademia.dto.CourseDTO;
import com.mitocode.mitoacademia.model.Course;


@Configuration
public class MapperConfig {

    @Bean("courseMapper")
    protected ModelMapper courseMapper() {
    	ModelMapper mapper = new ModelMapper();
    	
    	//Lectura
    	TypeMap<Course, CourseDTO> typeMap1 = mapper.createTypeMap(Course.class, CourseDTO.class);
    	typeMap1.addMapping(Course::getName, (dest, v)-> dest.setNameOfCourse((String)v));
    	
    	//Escritura
    	TypeMap<CourseDTO, Course> typeMap2 = mapper.createTypeMap(CourseDTO.class, Course.class);
    	typeMap2.addMapping(CourseDTO::getNameOfCourse, (dest, v)->dest.setName((String)v));
    	
    	return mapper;
	}
    
    @Bean("modelMapper")
    protected ModelMapper modelMapper() {
    	return new ModelMapper();
    }
}

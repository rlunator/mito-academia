package com.mitocode.mitoacademia.controller;

import com.mitocode.mitoacademia.dto.CourseDTO;
import com.mitocode.mitoacademia.model.Course;
import com.mitocode.mitoacademia.service.ICourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;
    
    @Qualifier("courseMapper")
    private final ModelMapper mapper;
    
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() throws Exception {
        List<CourseDTO> listCourse = service.readAll().stream()
                                        .map(this::convertToDto)
                                        .toList() ;
        return new ResponseEntity<>(listCourse, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Course Course = service.readById(id);
        return new ResponseEntity<>(convertToDto(Course), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseDTO Course) throws Exception{
        Course CourseR = service.save(convertToEntity(Course));
        return new ResponseEntity<>(convertToDto(CourseR), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@RequestBody @Valid CourseDTO CourseDTO, @PathVariable("id") Integer id)
    		throws Exception {
        Course Course = service.update(convertToEntity(CourseDTO), id);        
        return new ResponseEntity<>(convertToDto(Course), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)throws Exception {
        service.delete(id);        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private CourseDTO convertToDto(Course obj){
        return mapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }
}

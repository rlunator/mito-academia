package com.mitocode.mitoacademia.controller;

import com.mitocode.mitoacademia.dto.StudentDTO;
import com.mitocode.mitoacademia.model.Student;
import com.mitocode.mitoacademia.service.IStudentService;

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
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;
    
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() throws Exception {
        List<StudentDTO> listStudent = service.readAll().stream()
                                        .map(this::convertToDto)
                                        .toList() ;
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Student Student = service.readById(id);
        return new ResponseEntity<>(convertToDto(Student), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody @Valid StudentDTO student) throws Exception{
        Student StudentR = service.save(convertToEntity(student));
        return new ResponseEntity<>(convertToDto(StudentR), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody @Valid StudentDTO studentDTO, @PathVariable("id") Integer id)
    		throws Exception {
        Student Student = service.update(convertToEntity(studentDTO), id);        
        return new ResponseEntity<>(convertToDto(Student), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)throws Exception {
        service.delete(id);        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/orderAge")
    public ResponseEntity<List<StudentDTO>> getAllOrderAge() throws Exception {
        List<StudentDTO> listStudent = service.getStudentOrderAge();
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    
    private StudentDTO convertToDto(Student obj){
    	StudentDTO student = mapper.map(obj, StudentDTO.class);
    	student.calculateAge(student.getBirthDate());
        return student;
    }

    private Student convertToEntity(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }
}

package com.mitocode.mitoacademia.controller;

import com.mitocode.mitoacademia.dto.EnrollmentCuorseDTO;
import com.mitocode.mitoacademia.dto.EnrollmentDTO;
import com.mitocode.mitoacademia.model.Enrollment;
import com.mitocode.mitoacademia.service.IEnrollmentService;
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
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final IEnrollmentService iEnrollmentService;
    
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll() throws Exception {
        List<EnrollmentDTO> listEnrollment = iEnrollmentService.readAll().stream()
                                        .map(this::convertToDto)
                                        .toList() ;
        return new ResponseEntity<>(listEnrollment, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Enrollment Enrollment = iEnrollmentService.readById(id);
        return new ResponseEntity<>(convertToDto(Enrollment), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@RequestBody @Valid EnrollmentDTO enrollment) throws Exception{
        Enrollment EnrollmentR = iEnrollmentService.save(convertToEntity(enrollment));
        return new ResponseEntity<>(convertToDto(EnrollmentR), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@RequestBody @Valid EnrollmentDTO enrollment, @PathVariable("id") Integer id)
    		throws Exception {
        Enrollment Enrollment = iEnrollmentService.update(convertToEntity(enrollment), id);        
        return new ResponseEntity<>(convertToDto(Enrollment), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)throws Exception {
        iEnrollmentService.delete(id);        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/resume")
    public ResponseEntity<List<EnrollmentCuorseDTO>> getResumeEnrollment() throws Exception {
    	List<EnrollmentCuorseDTO> listEnrollment = iEnrollmentService .getResumeEnrollment();
        return new ResponseEntity<>(listEnrollment, HttpStatus.OK);
    }
    
    private EnrollmentDTO convertToDto(Enrollment obj){
        return mapper.map(obj, EnrollmentDTO.class);
    }

    private Enrollment convertToEntity(EnrollmentDTO dto){
        return mapper.map(dto, Enrollment.class);
    }
}

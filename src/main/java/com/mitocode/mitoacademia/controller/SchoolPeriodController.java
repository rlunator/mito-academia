package com.mitocode.mitoacademia.controller;

import com.mitocode.mitoacademia.dto.SchoolPeriodDTO;
import com.mitocode.mitoacademia.model.SchoolPeriod;
import com.mitocode.mitoacademia.service.ISchoolPeriodService;

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
@RequestMapping("/schoolPeriod")
@RequiredArgsConstructor
public class SchoolPeriodController {

    private final ISchoolPeriodService service;
    
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    
    @GetMapping
    public ResponseEntity<List<SchoolPeriodDTO>> getAll() throws Exception {
        List<SchoolPeriodDTO> listSchoolPeriod = service.readAll().stream()
                                        .map(this::convertToDto)
                                        .toList() ;
        return new ResponseEntity<>(listSchoolPeriod, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SchoolPeriodDTO> readById(@PathVariable("id") Integer id) throws Exception {
        SchoolPeriod SchoolPeriod = service.readById(id);
        return new ResponseEntity<>(convertToDto(SchoolPeriod), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolPeriodDTO> create(@RequestBody @Valid SchoolPeriodDTO schoolPeriod) throws Exception{
        SchoolPeriod SchoolPeriodR = service.save(convertToEntity(schoolPeriod));
        return new ResponseEntity<>(convertToDto(SchoolPeriodR), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SchoolPeriodDTO> update(@RequestBody @Valid SchoolPeriodDTO schoolPeriod, @PathVariable("id") Integer id)
    		throws Exception {
        SchoolPeriod SchoolPeriod = service.update(convertToEntity(schoolPeriod), id);        
        return new ResponseEntity<>(convertToDto(SchoolPeriod), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)throws Exception {
        service.delete(id);        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private SchoolPeriodDTO convertToDto(SchoolPeriod obj){
        return mapper.map(obj, SchoolPeriodDTO.class);
    }

    private SchoolPeriod convertToEntity(SchoolPeriodDTO dto){
        return mapper.map(dto, SchoolPeriod.class);
    }
}

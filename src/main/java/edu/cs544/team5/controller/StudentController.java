package edu.cs544.team5.controller;

import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentReadDto> createStudent(@Valid @RequestBody StudentCreationDto dto){
        StudentReadDto studentReadDto = studentService.createStudent(dto);
        return ResponseEntity.ok(studentReadDto);
    }

}

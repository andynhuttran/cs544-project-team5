package edu.cs544.team5.controller;

import edu.cs544.team5.dto.*;
import edu.cs544.team5.exception.StudentHandleException;
import edu.cs544.team5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentReadDto> createStudent(@Valid @RequestBody StudentCreationDto dto){
        try {
            StudentReadDto studentReadDto = studentService.createStudent(dto);
            return ResponseEntity.ok(studentReadDto);
        }
        catch (Exception exp){
            throw new StudentHandleException(HttpStatus.BAD_REQUEST, exp.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentReadDto> getStudent(@PathVariable int id){
        StudentReadDto studentReadDto = studentService.findById(id);
        return ResponseEntity.ok(studentReadDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.activeOrDisableStudent(id, false);
    }

    @PostMapping("/{id}")
    public void activeStudent(@PathVariable int id, @RequestParam String type){
        if ("active".equalsIgnoreCase(type)) {
            studentService.activeOrDisableStudent(id, true);
        }
        else {
            throw new StudentHandleException(HttpStatus.BAD_REQUEST, "Do not support type = " + type);
        }
    }

    @GetMapping("/{id}/courses")
    public List<StudentCourseDto> getCourse(@PathVariable int id, @RequestParam(required = false) String type){

        if ("past".equalsIgnoreCase(type)){
            return studentService.getPastCourseOffering(id);
        }
        else if ("current".equalsIgnoreCase(type)){
            return studentService.getCurrentCourseOffering(id);
        }
        else if ("future".equalsIgnoreCase(type)){
            return studentService.getFutureCourseOffering(id);
        }
        else {
            String error = "Require type parameter";
            if (type != null){
                error = "Do not support type = " + type;
            }
            throw new StudentHandleException(HttpStatus.BAD_REQUEST, error);
        }
    }

    @PostMapping("/{id}/registrations")
    public RegistrationReadDto getCourse(@PathVariable int id, @Valid @RequestBody RegistrationCreationDto dto){
        return studentService.registryCourse(id, dto);
    }



}

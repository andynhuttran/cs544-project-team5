package edu.cs544.team5.controller;

import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.CourseReadDto;
import edu.cs544.team5.dto.StudentCourseDto;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.exception.StudentControllerException;
import edu.cs544.team5.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
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

        StudentReadDto studentReadDto = studentService.createStudent(dto);
        return ResponseEntity.ok(studentReadDto);
    }

    @GetMapping("/{id}/courses")
    public List<StudentCourseDto> getCourse(@PathVariable int id, @RequestParam(required = false) String type){

        if (type == null){
            throw new StudentControllerException("Require type parameter");
        }

        if (type.equalsIgnoreCase("past")){
            return studentService.getPastCourseOffering(id);
        }
        else if (type.equalsIgnoreCase("current")){
            return studentService.getCurrentCourseOffering(id);
        }
        else if (type.equalsIgnoreCase("future")){
            return studentService.getFutureCourseOffering(id);
        }
        else {
            throw new StudentControllerException("Do not support type = " + type);
        }
    }



}

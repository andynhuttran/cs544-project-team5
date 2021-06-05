package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.dto.CourseReadDto;
import edu.cs544.team5.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/courses")
    public List<CourseReadDto> findAll(){
        List<Course> courseList= courseService.findAll();
        return courseList.stream()
                .map(c -> modelMapper.map(c, CourseReadDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> find(@PathVariable("id") Integer id){
        Course course = courseService.findById(id);
        if(course == null){
            return ResponseEntity.notFound().build();
        }
       else{
           CourseReadDto readDto = modelMapper.map(course, CourseReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }
}

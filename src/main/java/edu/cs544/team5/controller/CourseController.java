package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.dto.CourseCreationDto;
import edu.cs544.team5.dto.CourseReadDto;
import edu.cs544.team5.service.CourseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ResponseBody
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<CourseReadDto> findAll(){
        List<Course> courseList= courseService.findAll();
        List<CourseReadDto> courseReadDtos =  courseList.stream()
                .map(course -> modelMapper.map(course, CourseReadDto.class))
                .collect(Collectors.toList());
        return courseReadDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> find(@PathVariable("id") int id){
        Course course = courseService.findById(id);
        if(course == null){
            return ResponseEntity.notFound().build();
        }
       else{
           CourseReadDto readDto = modelMapper.map(course, CourseReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CourseReadDto> create(CourseCreationDto newCourse) {
        Course course = courseService.create(modelMapper.map(newCourse, Course.class));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(course, CourseReadDto.class));
    }
}

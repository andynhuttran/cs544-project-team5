package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.dto.CourseCreationDto;
import edu.cs544.team5.dto.CourseReadDto;
import edu.cs544.team5.service.AbstractService;
import edu.cs544.team5.service.CourseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ResponseBody
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private AbstractService<Course> courseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<CourseReadDto> findAll() {
        List<Course> courseList = courseService.findAll();
        return courseList.stream()
                .map(course -> modelMapper.map(course, CourseReadDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> find(@PathVariable("id") int id) {
        Course course = courseService.findById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        } else {
            CourseReadDto readDto = modelMapper.map(course, CourseReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CourseReadDto> create(@Valid @RequestBody CourseCreationDto newCourseDto) {
        Course newCourse = modelMapper.map(newCourseDto, Course.class);
        Course course = courseService.create(newCourse);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(course, CourseReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReadDto> update(@Valid @RequestBody CourseCreationDto course, @PathVariable int id) {
        Course updatedCourse = modelMapper.map(course, Course.class);
        updatedCourse.setId(id);
        Course responseCourse = courseService.update(updatedCourse);

        if (responseCourse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(responseCourse, CourseReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

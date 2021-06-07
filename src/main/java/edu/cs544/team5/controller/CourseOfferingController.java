package edu.cs544.team5.controller;

import edu.cs544.team5.domain.AcademicBlock;
import edu.cs544.team5.domain.Course;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Faculty;
import edu.cs544.team5.dto.CourseOfferingCreationDto;
import edu.cs544.team5.dto.CourseOfferingReadDto;
import edu.cs544.team5.service.AbstractService;
import edu.cs544.team5.service.CourseOfferingServiceImpl;
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
@RequestMapping("/course-offering")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingServiceImpl courseOfferingService;

    @Autowired
    private AbstractService<Course> courseService;

    @Autowired
    private AbstractService<Faculty> facultyService;

    @Autowired
    private AbstractService<AcademicBlock> blockService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<CourseOfferingReadDto> findAll() {
        List<CourseOffering> CourseOfferingList = courseOfferingService.findAll();
        return CourseOfferingList.stream()
                .map(CourseOffering -> modelMapper.map(CourseOffering, CourseOfferingReadDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/faculty/{id}")
    public ResponseEntity<List<CourseOfferingReadDto>> findAllByFaculty(@PathVariable("id") int id) {
        Faculty faculty = facultyService.findById(id);
        if(faculty == null){
            return ResponseEntity.notFound().build();
        }
        List<CourseOffering> byFaculty = courseOfferingService.findByFaculty(faculty);

        List<CourseOfferingReadDto> courseOfferingReadDtoList = byFaculty.stream()
                .map(CourseOffering -> modelMapper.map(CourseOffering, CourseOfferingReadDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(courseOfferingReadDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOfferingReadDto> find(@PathVariable("id") int id) {
        CourseOffering courseOffering = courseOfferingService.findById(id);
        if (courseOffering == null) {
            return ResponseEntity.notFound().build();
        } else {
            CourseOfferingReadDto readDto = modelMapper.map(courseOffering, CourseOfferingReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CourseOfferingReadDto> create(CourseOfferingCreationDto newCourseOffering) {
        CourseOffering courseOffering = modelMapper.map(newCourseOffering, CourseOffering.class);
        Course course = courseService.findById(newCourseOffering.getCourseId());
        courseOffering.setCourse(course);

        Faculty faculty = facultyService.findById(newCourseOffering.getFacultyId());
        courseOffering.setFaculty(faculty);

        AcademicBlock academicBlock = blockService.findById(newCourseOffering.getAcademicBlockId());
        courseOffering.setBlock(academicBlock);

        courseOffering = courseOfferingService.create(courseOffering);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(courseOffering.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(courseOffering, CourseOfferingReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseOfferingReadDto> update(@RequestBody CourseOffering CourseOffering, @PathVariable int id) {
        CourseOffering.setId(id);
        CourseOffering updatedCourseOffering = courseOfferingService.update(CourseOffering);

        if (updatedCourseOffering == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(updatedCourseOffering, CourseOfferingReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        courseOfferingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

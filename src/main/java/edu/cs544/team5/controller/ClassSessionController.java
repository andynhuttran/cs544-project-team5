package edu.cs544.team5.controller;

import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Location;
import edu.cs544.team5.domain.Timeslot;
import edu.cs544.team5.dto.ClassSessionCreationDto;
import edu.cs544.team5.dto.ClassSessionReadDto;
import edu.cs544.team5.service.AbstractService;
import edu.cs544.team5.service.ClassSessionService;
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
@RequestMapping("/class-session")
public class ClassSessionController {

    @Autowired
    private ClassSessionService classSessionService;

    @Autowired
    private AbstractService<CourseOffering> courseOfferingService;

    @Autowired
    private AbstractService<Location> locationService;

    @Autowired
    private AbstractService<Timeslot> timeslotService;


    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<ClassSessionReadDto> findAll() {
        List<ClassSession> ClassSessionList = classSessionService.findAll();
        return ClassSessionList.stream()
                .map(ClassSession -> modelMapper.map(ClassSession, ClassSessionReadDto.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClassSessionReadDto> find(@PathVariable("id") int id) {
        ClassSession ClassSession = classSessionService.findById(id);
        if (ClassSession == null) {
            return ResponseEntity.notFound().build();
        } else {
            ClassSessionReadDto readDto = modelMapper.map(ClassSession, ClassSessionReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ClassSessionReadDto> create(@Valid @RequestBody ClassSessionCreationDto newClassSession) {
        ClassSession classSession = modelMapper.map(newClassSession, ClassSession.class);

        CourseOffering courseOffering = courseOfferingService.findById(newClassSession.getCourseOfferingId());
        if (courseOffering == null)
            return ResponseEntity.badRequest().build();

        classSession.setCourseOffering(courseOffering);

        Location location = locationService.findById(newClassSession.getLocationId());
        if (location == null)
            return ResponseEntity.badRequest().build();

        classSession.setLocation(location);

        Timeslot timeslot = timeslotService.findById(newClassSession.getCourseOfferingId());
        if (timeslot == null)
            return ResponseEntity.badRequest().build();

        classSession.setTimeslot(timeslot);

        classSession = classSessionService.create(classSession);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(classSession.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(classSession, ClassSessionReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassSessionReadDto> update(@Valid @RequestBody ClassSessionCreationDto sessionCreationDto, @PathVariable int id) {


        CourseOffering courseOffering = courseOfferingService.findById(sessionCreationDto.getCourseOfferingId());
        if (courseOffering == null)
            return ResponseEntity.badRequest().build();


        Location location = locationService.findById(sessionCreationDto.getLocationId());
        if (location == null)
            return ResponseEntity.badRequest().build();


        Timeslot timeslot = timeslotService.findById(sessionCreationDto.getCourseOfferingId());
        if (timeslot == null)
            return ResponseEntity.badRequest().build();

        ClassSession classSession = modelMapper.map(sessionCreationDto, ClassSession.class);

        classSession.setCourseOffering(courseOffering);
        classSession.setLocation(location);
        classSession.setTimeslot(timeslot);

        ClassSession ClassSession = modelMapper.map(sessionCreationDto, ClassSession.class);
        ClassSession.setId(id);

        ClassSession updatedClassSession = classSessionService.update(ClassSession);

        if (updatedClassSession == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(updatedClassSession, ClassSessionReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        classSessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

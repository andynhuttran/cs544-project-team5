package edu.cs544.team5.service;

import edu.cs544.team5.dto.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface StudentService {

    public StudentReadDto createStudent(StudentCreationDto dto);
    public StudentReadDto getOneStudent(int id);
    public void activeOrDisableStudent(int id, boolean active);


    public List<StudentCourseDto> getPastCourseOffering(int id);
    public List<StudentCourseDto> getCurrentCourseOffering(int id);
    public List<StudentCourseDto> getFutureCourseOffering(int id);

    public RegistrationReadDto registryCourse(int id, RegistrationCreationDto dto);
}

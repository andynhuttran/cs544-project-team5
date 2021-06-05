package edu.cs544.team5.service;

import edu.cs544.team5.dto.CourseReadDto;
import edu.cs544.team5.dto.StudentCourseDto;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;

import java.util.List;

public interface StudentService {

    public StudentReadDto createStudent(StudentCreationDto dto);

    public List<StudentCourseDto> getPastCourseOffering(int id);
    public List<StudentCourseDto> getCurrentCourseOffering(int id);
    public List<StudentCourseDto> getFutureCourseOffering(int id);
}

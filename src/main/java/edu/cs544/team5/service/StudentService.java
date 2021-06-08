package edu.cs544.team5.service;

import edu.cs544.team5.dto.StudentCourseDto;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;

import java.util.List;

public interface StudentService {

    StudentReadDto createStudent(StudentCreationDto dto);

    StudentReadDto findById(Integer id);

    List<StudentCourseDto> getPastCourseOffering(int id);

    List<StudentCourseDto> getCurrentCourseOffering(int id);

    List<StudentCourseDto> getFutureCourseOffering(int id);
}

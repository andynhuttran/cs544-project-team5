package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class CourseOfferingReadDto {

    private Integer id;
    private String period;
    private LocalDate startDate;
    private int capacity;

    private CourseReadDto courseReadDto;
    private FacultyReadDto facultyReadDto;
    private AcademicBlockReadDto block;
    private Collection<RegistrationReadDto> registrationReadDtos;
}

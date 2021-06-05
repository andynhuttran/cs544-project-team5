package edu.cs544.team5.service;

import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;

public interface StudentService {

    public StudentReadDto createStudent(StudentCreationDto dto);
}

package edu.cs544.team5.service;

import edu.cs544.team5.dto.*;

import java.util.List;

public interface StudentService {

    StudentReadDto createStudent(StudentCreationDto dto);

    StudentReadDto getOneStudent(int id);

    void activeOrDisableStudent(int id, boolean active);

    StudentReadDto findById(Integer id);

    StudentReadDto findByBarcode(String barcode);

    List<StudentCourseDto> getPastCourseOffering(int id);

    List<StudentCourseDto> getCurrentCourseOffering(int id);

    List<StudentCourseDto> getFutureCourseOffering(int id);

    RegistrationReadDto registryCourse(int id, RegistrationCreationDto dto);
}

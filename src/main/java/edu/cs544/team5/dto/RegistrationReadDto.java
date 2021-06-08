package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegistrationReadDto {

    LocalDate date;
    List<StudentCourseDto> courses = new ArrayList<>();
}

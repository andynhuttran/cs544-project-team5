package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCourseDto {

    private String courseName;
    private String courseDescription;

    private String facultyName;

    private LocalDate begin;
    private LocalDate end;

    private String semester;
}

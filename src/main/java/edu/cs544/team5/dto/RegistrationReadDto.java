package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class RegistrationReadDto {

    private Integer id;
    private LocalDate date;
    private CourseOfferingReadDto offering;
    private StudentReadDto studentReadDto;

}

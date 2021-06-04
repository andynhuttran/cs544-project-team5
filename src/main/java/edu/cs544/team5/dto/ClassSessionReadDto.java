package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ClassSessionReadDto {

    private Integer id;
    private LocalDate date;
    private TimeslotReadDto timeslotReadDto;
    private CourseOfferingReadDto courseOfferingReadDto;
    private LocationReadDto locationReadDto;

}

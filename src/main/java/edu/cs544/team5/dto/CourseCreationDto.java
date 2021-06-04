package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseCreationDto {

    @NotNull
    private String name;

    @NotNull
    private String abbreviation;

    @NotNull
    private String description;
}


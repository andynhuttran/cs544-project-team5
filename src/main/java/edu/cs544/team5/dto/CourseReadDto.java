package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;


@Setter
@Getter
public class CourseReadDto {

    private Integer id;

    private String name;

    private String abbreviation;

    private String description;
}


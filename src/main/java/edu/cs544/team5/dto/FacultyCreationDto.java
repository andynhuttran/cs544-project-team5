package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FacultyCreationDto extends PersonCreationDto {
    @NotNull
    private String title;
}

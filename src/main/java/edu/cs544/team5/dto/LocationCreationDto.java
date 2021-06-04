package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LocationCreationDto {

    @NotNull
    private String locationName;
    @NotNull
    private String description;
}

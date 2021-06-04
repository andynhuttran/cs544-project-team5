package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AcademicBlockCreationDto {

    @NotNull
    private String name;

    @NotNull
    private LocalDate beginDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private String semester;
}

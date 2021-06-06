package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CourseOfferingCreationDto {

    @NotNull
    private String period;
    private LocalDate startDate;
    private int capacity;

    @NotNull
    private int courseId;

    @NotNull
    private int facultyId;

    @NotNull
    private int academicBlockId;

}

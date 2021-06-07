package edu.cs544.team5.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CourseOfferingCreationDto {

    @NotNull
    private String period;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    private int capacity;

    @NotNull
    private int courseId;

    @NotNull
    private int facultyId;

    @NotNull
    private int academicBlockId;

}

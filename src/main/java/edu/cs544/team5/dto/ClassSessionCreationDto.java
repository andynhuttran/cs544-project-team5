package edu.cs544.team5.dto;

import edu.cs544.team5.domain.CourseOffering;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class ClassSessionCreationDto {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull
    private TimeslotCreationDto timeslot;

    @NotNull
    private CourseOffering courseOffering;

    @NotNull
    private LocationCreationDto locationCreationDto;
}

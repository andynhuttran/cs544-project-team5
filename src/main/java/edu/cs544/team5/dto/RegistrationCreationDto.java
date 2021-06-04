package edu.cs544.team5.dto;

import edu.cs544.team5.domain.CourseOffering;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class RegistrationCreationDto {

    @NotNull
    private LocalDate date;
    @NotNull
    private CourseOffering offering;
    @NotNull
    private StudentCreationDto studentCreationDto;
}

package edu.cs544.team5.dto;

import edu.cs544.team5.domain.CourseOffering;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class RegistrationCreationDto {

    @NotEmpty
    private int[] courseOfferings;
}

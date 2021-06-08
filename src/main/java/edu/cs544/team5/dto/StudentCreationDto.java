package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class StudentCreationDto {
    /**
     * @Todo
     * inherit these properties from PersonDTO
     */
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String studentId;

    @NotNull
    private LocalDate entryDate;
}

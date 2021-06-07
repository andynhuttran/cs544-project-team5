package edu.cs544.team5.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
public class StudentCreationDto {

    private String firstName;

    private String lastName;

    private String studentId;

    @NotNull
    private LocalDate entryDate;
}

package edu.cs544.team5.dto;

import lombok.Data;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Data
public class StudentCreationDto extends PersonCreationDto {

    @NotNull
    private String visaStatus;

    @NotNull
    private String status;

    @NotNull
    private String track;

    @NotNull
    private LocalDate entryDate;

    @NotNull

    private String barcode;

    @OneToMany(mappedBy = "student")
    private Collection<RegistrationCreationDto> registrationCreationDtos;
}
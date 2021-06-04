package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class StudentReadDto extends PersonReadDto {
    private String studentId;
    private String visaStatus;
    private String status;
    private String track;
    private LocalDate entryDate;
    private String barcode;

    private Collection<RegistrationReadDto> registrationReadDtos;
}

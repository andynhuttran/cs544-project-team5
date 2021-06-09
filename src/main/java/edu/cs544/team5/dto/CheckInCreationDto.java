package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
public class CheckInCreationDto {
    @Valid
    private String studentBarcode;

    @Valid
    private Integer classSessionId;
}

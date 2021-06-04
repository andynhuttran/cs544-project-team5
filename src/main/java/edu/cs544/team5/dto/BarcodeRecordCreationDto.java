package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BarcodeRecordCreationDto {

    @NotNull
    private LocalDateTime attendance;

    @NotNull
    private StudentCreationDto studentCreationDto;

    @NotNull
    private ClassSessionCreationDto classSessionCreationDto;
}

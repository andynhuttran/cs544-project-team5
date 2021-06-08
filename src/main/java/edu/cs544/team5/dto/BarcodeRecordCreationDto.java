package edu.cs544.team5.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BarcodeRecordCreationDto {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime attendance;

    @Valid
    private StudentReadDto studentReadDto;

    @Valid
    private ClassSessionReadDto classSessionReadDto;
}

package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarcodeRecordReadDto {

    private Integer id;
    private LocalDateTime attendance;
    private StudentReadDto studentReadDto;
    private ClassSessionReadDto classSessionReadDto;
}

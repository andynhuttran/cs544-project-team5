package edu.cs544.team5.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@ToString
public class StudentReadDto extends PersonReadDto {

    private String studentId;

    private LocalDate entryDate;

    private String barcode;
}

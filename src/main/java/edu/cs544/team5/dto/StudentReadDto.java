package edu.cs544.team5.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
public class StudentReadDto {

    private int id;

    private String firstName;

    private String lastName;

    private String studentId;

    private LocalDate entryDate;

    private String barcode;

    @Override
    public String toString() {
        return "StudentReadDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", entryDate=" + entryDate +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}

package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class StudentCreationDto extends PersonCreationDto {
    @NotNull
    private String studentId;

    @NotNull
    private LocalDate entryDate;

    public StudentCreationDto(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password, @Valid Set<RoleCreationDto> roles, String studentId, LocalDate entryDate) {
        super(firstName, lastName, username, password, roles);
        this.studentId = studentId;
        this.entryDate = entryDate;
    }
}

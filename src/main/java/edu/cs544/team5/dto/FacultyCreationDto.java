package edu.cs544.team5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class FacultyCreationDto extends PersonCreationDto {
    @NotNull
    private String title;

    public FacultyCreationDto(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password, @Valid Set<RoleCreationDto> roles) {
        super(firstName, lastName, username, password, roles);
    }
}

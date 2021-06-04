package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class PersonCreationDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;

    private Set<RoleCreationDto> roles;
}

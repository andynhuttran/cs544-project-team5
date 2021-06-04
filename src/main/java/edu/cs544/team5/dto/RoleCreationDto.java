package edu.cs544.team5.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Data
public class RoleCreationDto {

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleTypeCreationDto type;

}


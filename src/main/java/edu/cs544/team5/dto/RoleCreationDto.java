package edu.cs544.team5.dto;

import edu.cs544.team5.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreationDto {

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType type;

}


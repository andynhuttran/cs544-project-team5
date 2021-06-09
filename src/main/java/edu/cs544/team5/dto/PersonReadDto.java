package edu.cs544.team5.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PersonReadDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Set<RoleReadDto> roleReadDtos;
}

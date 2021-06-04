package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Setter
@Getter
public class Person {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @Enumerated
    private Role role;
}

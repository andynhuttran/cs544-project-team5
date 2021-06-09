package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "courseDescription")
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(length = 5, unique = true)
    private String abbreviation;

    @Column(table = "courseDescription", length = 4000)
    private String description;
}


package edu.cs544.team5.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "courseDescription")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 5)
    private String abbreviation;

    @Column(table = "courseDescription", length = 4000)
    private String description;
}


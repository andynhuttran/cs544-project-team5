package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

@SecondaryTable(name = "courseDescription")
@Entity
@Setter
@Getter
public class Course {
    @Id
    @Column(length = 5, nullable = false)
    private String number;
    private String name;
    @Column(length = 5)
    private String abbreviation;
    @Column(table="courseDescription", length=4000)
    private String description;
}


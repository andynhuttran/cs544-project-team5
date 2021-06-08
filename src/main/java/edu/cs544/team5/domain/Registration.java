package edu.cs544.team5.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Registration {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate date;

    @ManyToOne
    private CourseOffering offering;

    @ManyToOne
    private Student student;
}

package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Setter
@Getter
public class ClassSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    private Timeslot timeslot;
    @ManyToOne
    private CourseOffering courseOffering;
    @ManyToOne
    private Location location;
}

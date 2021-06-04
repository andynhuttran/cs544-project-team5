package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Setter
@Getter
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String period;
    private LocalDate startDate;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "courseNumber")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "facultyId")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "blockId")
    private AcademicBlock block;
    @OneToMany(mappedBy = "offering")
    private Collection<Registration> registrations;
}

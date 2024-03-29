package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class CourseOffering {
    @Id
    @GeneratedValue
    private Integer id;
    private String period;
    private LocalDate startDate;
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blockId")
    private AcademicBlock block;

    @OneToMany(mappedBy = "offering", fetch = FetchType.LAZY)
    private Set<Registration> registrations = new HashSet<>();

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", startDate=" + startDate +
                '}';
    }


    public void addRegistration(Registration registration){
        if (registrations.add(registration)){
            registration.setOffering(this);
        }
    }
}

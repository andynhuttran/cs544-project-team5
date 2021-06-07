package edu.cs544.team5.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    @Column(length = 11, nullable = false)
    private String studentId;

    private String visaStatus;

    private String status;

    private String track;

    private LocalDate entryDate;

    @Column(length = 13, nullable = false, unique = true)
    private String barcode;

    @OneToMany(mappedBy = "student")
    private Collection<Registration> registrations;
}

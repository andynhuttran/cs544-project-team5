package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Setter
@Getter
public class Student extends Person {
    @Column(length = 11, nullable = false)
    private String studentId;
    private String visaStatus;
    private String status;
    private String track;
    private LocalDate entryDate;
    private String barcodeNo;
    @Column(length = 13, nullable = false, unique = true)
    private String barcode;
    @OneToMany(mappedBy = "student")
    private Collection<Registration> registrations;
}

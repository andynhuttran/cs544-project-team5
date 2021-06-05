package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
public class Student extends Person {
    @Column(length = 11, nullable = false, unique = true)
    private String studentId;

    private LocalDate entryDate;

    @Column(length = 50, nullable = false, unique = true)
    private String barcode;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Registration> registrations;


    public boolean addRegistration(Registration registration){
        if (registrations.add(registration)){
            registration.setStudent(this);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", entryDate=" + entryDate +
                ", barcode='" + barcode + '\'' +
                ", person='" + super.toString() + '\'' +
                '}';
    }
}

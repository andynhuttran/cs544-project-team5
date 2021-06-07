package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
public class BarcodeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    private CourseSession courseSession;
}

package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class BarcodeRecord {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime attendance;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    private ClassSession classSession;
}

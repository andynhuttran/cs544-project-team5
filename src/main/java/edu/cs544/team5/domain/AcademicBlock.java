package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class AcademicBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    
    private LocalDate beginDate;

    @Column(nullable = false)
    
    private LocalDate endDate;

    @Column(nullable = false)
    private String semester;
}

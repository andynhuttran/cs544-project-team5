package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class Timeslot {
    @Id
    @GeneratedValue
    private Integer id;

    @NaturalId
    private String timeslotId;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
}

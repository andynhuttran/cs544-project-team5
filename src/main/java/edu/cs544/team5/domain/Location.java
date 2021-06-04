package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue
    private Long id;
    private String locationName;
    private String desc;
}

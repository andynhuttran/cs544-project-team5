package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String locationName;
    private String description;
}

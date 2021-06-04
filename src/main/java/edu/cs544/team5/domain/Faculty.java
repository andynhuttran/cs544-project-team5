package edu.cs544.team5.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Faculty extends Person {
    private String title;
}

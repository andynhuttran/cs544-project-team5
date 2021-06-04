package edu.cs544.team5.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum Role {
    ADMIN, STUDENT, FACULTY, PERSONNEL
}

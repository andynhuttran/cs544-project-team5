package edu.cs544.team5.domain;

public enum RoleType {
    ADMIN("ADMIN"), STUDENT("STUDENT"), FACULTY("FACULTY"), PERSONNEL("PERSONNEL");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

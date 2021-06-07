package edu.cs544.team5.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;

    @Setter(AccessLevel.NONE)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<RoleType> roles = new ArrayList<>();

    public Person(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public void addRole(RoleType role) {
        roles.add(role);
    }

    public void removeRole(RoleType role) {
        roles.remove(role);
    }

    public Collection<RoleType> getRoles() {
        return Collections.unmodifiableCollection(roles);
    }
}

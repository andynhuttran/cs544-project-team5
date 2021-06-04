package edu.cs544.team5.domain;

import javax.persistence.*;




@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RoleType type;

}


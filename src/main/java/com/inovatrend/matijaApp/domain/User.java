package com.inovatrend.matijaApp.domain;

import lombok.*;

import javax.persistence.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex")
    private String sex;
/*
    @Column(name = "sex")
    private Sex male = male.MALE;

    @Column(name = "sex")
    private Sex female = female.FEMALE; */

}



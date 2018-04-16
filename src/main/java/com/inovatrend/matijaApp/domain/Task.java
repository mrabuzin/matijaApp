package com.inovatrend.matijaApp.domain;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

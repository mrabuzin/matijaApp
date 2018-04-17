package com.inovatrend.matijaApp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

package com.example.contactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String name;
    private String secondName;
    private String work;
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(length = 1000)
    private String description;

    @ManyToOne
    private User user;
}

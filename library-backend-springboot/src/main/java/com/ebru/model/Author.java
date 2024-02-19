package com.ebru.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private long id;
    @Column(name = "FIRST_NAME", length = 75)
    private String firstName;
    @Column(name = "LAST_NAME", length = 75)
    private String lastName;


    // 1             m
    //Author        Books
    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Book> bookSet = new HashSet<>();

    public Author (String firstname, String lastname){
        this.firstName = firstname;
        this.lastName = lastname;
    }
}

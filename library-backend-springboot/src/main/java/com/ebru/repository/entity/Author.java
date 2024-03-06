package com.ebru.repository.entity;

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
    private Long id;
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;


    // 1             m
    //Author        Books
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Set<Book> bookSet = new HashSet<>();

    public Author (String firstname, String lastname){
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public Author (Long id,String firstname, String lastname){
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
    }
    public void removeBooks() {
        this.bookSet.clear();
    }
}

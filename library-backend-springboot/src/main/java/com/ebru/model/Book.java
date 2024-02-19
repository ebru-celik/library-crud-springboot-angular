package com.ebru.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "BOOK_NAME", length = 100)
    private String bookName;
    @Column(name = "STATUS", nullable = false )
    private boolean status;


    // m            1
    //books        Author
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public Book(String bookName, boolean status) {
        this.bookName = bookName;
        this.status = status;
    }
}

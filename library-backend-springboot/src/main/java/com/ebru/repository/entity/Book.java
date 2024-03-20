package com.ebru.repository.entity;

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
    private Long id;
    @Column(name = "BOOK_NAME", length = 100)
    private String bookName;
    @Column(name = "AVAILABLE", nullable = false )
    private boolean available;

    public Book(String bookName, boolean available) {
        this.bookName = bookName;
        this.available = available;
    }
}

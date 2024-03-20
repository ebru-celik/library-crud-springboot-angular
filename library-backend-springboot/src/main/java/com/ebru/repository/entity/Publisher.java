package com.ebru.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private Long id;
    @Column(name = "PUBLISHER_NAME")
    private String publisher_name;

    // 1             m
    //Publisher        authors
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Set<Author> authorSet = new HashSet<>();
}

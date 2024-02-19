package com.ebru.controller;

import com.ebru.model.Author;
import com.ebru.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api") //  http://localhost:8080/api
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //  http://localhost:8080/api/authors
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    //  http://localhost:8080/api/authors/1
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id){ return authorService.getAuthorById(id);}

}

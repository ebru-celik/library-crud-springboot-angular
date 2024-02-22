package com.ebru.controller;

import com.ebru.model.Author;
import com.ebru.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") long id) {
        return authorService.deleteAuthor(id);
    }

    @DeleteMapping("/authors")
    public ResponseEntity<HttpStatus> deleteAllAuthors() {
        return authorService.deleteAllAuthors();
    }
}

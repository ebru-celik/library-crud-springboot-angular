package com.ebru.controller;

import com.ebru.controller.model.AuthorRestResponse;
import com.ebru.fault.exception.RestException;
import com.ebru.repository.entity.Author;
import com.ebru.service.model.AuthorDetail;
import com.ebru.service.model.AuthorInfo;
import com.ebru.controller.model.AuthorRestRequest;
import com.ebru.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api") //  http://localhost:8080/api
public class AuthorController {
    private static final String ALPHANUM_PATTERN = "^[a-zA-Z0-9]*$";
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
    public ResponseEntity<AuthorRestResponse> addAuthor(@RequestBody @Valid AuthorRestRequest author) {
        if(!author.getFirstName().matches(ALPHANUM_PATTERN)){
            throw new RestException("Firstname should be alpha num characters");
        }
        final AuthorInfo authorInfo = new AuthorInfo(author.getFirstName(), author.getLastName());
        AuthorDetail authorDetail = authorService.addAuthor(authorInfo);
        AuthorRestResponse authorResponse = new AuthorRestResponse(authorDetail);

        return ResponseEntity.ok(authorResponse);
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

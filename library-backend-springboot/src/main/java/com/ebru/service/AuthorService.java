package com.ebru.service;

import com.ebru.model.Author;
import com.ebru.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    public ResponseEntity<Author> getAuthorById(Long id) {
        Optional<Author> authorData = authorRepository.findById(id);

        return authorData.map(author -> new ResponseEntity<>(author, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Author> addAuthor (Author author){
        Author authorObj = authorRepository.save(new Author(author.getFirstName(),author.getLastName()));
        return new ResponseEntity<>(authorObj, HttpStatus.CREATED);
    }

    public ResponseEntity<Author> updateAuthor (Long id, Author author){
        Optional<Author> opt = authorRepository.findById(id);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorObj = opt.get();
        authorObj.setFirstName(author.getFirstName());
        authorObj.setLastName(author.getLastName());

        return new ResponseEntity<>(authorRepository.save(authorObj), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllAuthors() {
        try {
            authorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

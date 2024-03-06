package com.ebru.service;

import com.ebru.fault.exception.BusinessException;
import com.ebru.repository.entity.Author;
import com.ebru.service.model.AuthorDetail;
import com.ebru.service.model.AuthorInfo;
import com.ebru.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional( readOnly = true )
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Transactional( readOnly = true )
    public ResponseEntity<Author> getAuthorById(Long id) {
        Optional<Author> authorData = authorRepository.findById(id);

        return authorData.map(author -> new ResponseEntity<>(author, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public AuthorDetail addAuthor (AuthorInfo author){
        if(author.getFirstName().equalsIgnoreCase(author.getLastName())){
            throw new BusinessException("Firstname and Lastname can not be same");
        }
        if (authorRepository.countByFirstNameAndLastName(author.getFirstName(), author.getLastName())>0){
            throw new BusinessException("Firstname and Lastname exist");
        }

        // transient Object
        Author authorEntity = new Author(author.getFirstName(), author.getLastName());
        // Persistent Object
        Author persistentAuthor = authorRepository.save(authorEntity);

        return AuthorDetail.builder()
                .firstName(persistentAuthor.getFirstName())
                .lastName(persistentAuthor.getLastName())
                .id(persistentAuthor.getId())
                .build();
    }

    @Transactional
    public ResponseEntity<Author> updateAuthor (Long id, Author author){
        Optional<Author> opt = authorRepository.findById(id);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorObj = opt.get();
        authorObj.setFirstName(author.getFirstName()); // detach Object
        authorObj.setLastName(author.getLastName());

        return new ResponseEntity<>(authorRepository.save(authorObj), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteAllAuthors() {
        try {
            authorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.ebru.service;

import com.ebru.model.Author;
import com.ebru.model.Book;
import com.ebru.repository.AuthorRepository;
import com.ebru.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public ResponseEntity<List<Book>> getAllBooks(String name) {
        List<Book> books = new ArrayList<>();
        if (name == null)
            bookRepository.findAll().forEach(books::add);
        else
            bookRepository.findByBookName(name).forEach(books::add);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookById(Long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        return bookData.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Book>> getAllBooksByAuthorId(Long authorId){
        Optional<Author> opt = authorRepository.findById(authorId);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorObj = opt.get();
        List<Book> books = new ArrayList<>();
        books.addAll(authorObj.getBookSet());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> addBook(Long authorId, Book book) {
        Optional<Author> opt = authorRepository.findById(authorId);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book bookObj = opt.map(author ->{
            author.getBookSet().add(book);
            return bookRepository.save(book);
        }).get();

        return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
    }

    public ResponseEntity<Book> updateBook(Long id, Book book) {
        Optional<Book> opt = bookRepository.findById(id);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book bookObj = opt.get();
        bookObj.setBookName(book.getBookName());
        bookObj.setAvailable(book.isAvailable());

        return new ResponseEntity<>(bookRepository.save(bookObj), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> deleteAllBooksOfAuthor(Long authorId) {
        Optional<Author> opt = authorRepository.findById(authorId);
        if(!opt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorObj = opt.get();
        authorObj.removeBooks();
        authorRepository.save(authorObj);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<List<Book>> findByAvailability() {
        try {
            List<Book> books = bookRepository.findByAvailable(true);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

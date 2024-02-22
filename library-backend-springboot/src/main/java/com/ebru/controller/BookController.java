package com.ebru.controller;

import com.ebru.model.Book;
import com.ebru.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api") //  http://localhost:8080/api
public class BookController {

    private final BookService bookService;
    // injection in Constructor
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public String hello(){
        return  " Hello Library";
    }

    //  http://localhost:8080/api/books

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String name) {
        return bookService.getAllBooks(name);
    }

    //  http://localhost:8080/api/books/1
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    //  http://localhost:8080/api/authors/1/books
    @GetMapping("/authors/{authorsId}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(@PathVariable("authorsId") Long authorsId){
        return bookService.getAllBooksByAuthorId(authorsId);
    }

    //  http://localhost:8080/api/authors/1/books
    @PostMapping("/authors/{authorsId}/books")
    public ResponseEntity<Book> createBook(@PathVariable("authorsId") Long authorsId, @RequestBody Book book) {
        return bookService.addBook(authorsId,book);
    }

    //  http://localhost:8080/api/books/1
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    //  http://localhost:8080/api/books/1
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

    //  http://localhost:8080/api/books
    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        return bookService.deleteAllBooks();
    }

    // http://localhost:8080/api/authors/1/books
    @DeleteMapping("/authors/{authorsId}/books")
    public ResponseEntity<List<Book>> deleteAllBooksOfAuthor(@PathVariable(value = "authorId") Long authorId) {
        return bookService.deleteAllBooksOfAuthor(authorId);
    }

    //  http://localhost:8080/api/books/available
    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> findByAvailablity() {
        return bookService.findByAvailability();
    }

}

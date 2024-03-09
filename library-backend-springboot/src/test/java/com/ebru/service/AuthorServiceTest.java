package com.ebru.service;

import com.ebru.fault.exception.BusinessException;
import com.ebru.repository.AuthorRepository;
import com.ebru.repository.entity.Author;
import com.ebru.service.model.AuthorDetail;
import com.ebru.service.model.AuthorInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addAuthor_WithNonExistingAuthor_ShouldReturnAuthorDetail() {
        // Arrange
        AuthorInfo authorInfo = new AuthorInfo("John", "Doe");
        when(authorRepository.countByFirstNameAndLastName("John", "Doe")).thenReturn(0);
        when(authorRepository.save(new Author("John", "Doe"))).thenReturn(new Author(1L, "John", "Doe"));

        // Act
        AuthorDetail authorDetail = authorService.addAuthor(authorInfo);

        // Assert
        assertNotNull(authorDetail);
        assertEquals("John", authorDetail.getFirstName());
        assertEquals("Doe", authorDetail.getLastName());
    }



    @Test
    void addAuthor_WithExistingAuthor_ShouldThrowBusinessException() {
        // Arrange
        AuthorInfo authorInfo = new AuthorInfo("John", "Doe");
        when(authorRepository.countByFirstNameAndLastName("John", "Doe")).thenReturn(1);

        // Act and Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authorService.addAuthor(authorInfo);
        });
        assertEquals("Firstname and Lastname exist", exception.getMessage());
    }

    @Test
    void addAuthor_WithSameFirstNameAndLastName_ShouldThrowBusinessException() {
        // Arrange
        AuthorInfo authorInfo = new AuthorInfo("John", "John");

        // Act and Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authorService.addAuthor(authorInfo);
        });
        assertEquals("Firstname and Lastname can not be same", exception.getMessage());
    }
}
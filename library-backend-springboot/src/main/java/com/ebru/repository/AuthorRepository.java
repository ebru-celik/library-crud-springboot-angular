package com.ebru.repository;

import com.ebru.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Integer countByFirstNameAndLastName(String firstName, String lastName);

}

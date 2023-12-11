package com.example.restprac.data.repository;

import com.example.restprac.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //Book save(Book book);
    //Optional<Book> findById(Long number);
    Optional<Book> findByTitle(String title);
    List<Book> findAll();
}

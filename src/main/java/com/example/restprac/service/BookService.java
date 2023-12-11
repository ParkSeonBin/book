package com.example.restprac.service;

import com.example.restprac.data.dto.BookDto;
import com.example.restprac.data.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto getBookId(Long number);

    Optional<Book> getBookByTitle(String title);

    List<Book> getAllBook();

    BookDto saveBook(BookDto bookDto);

    BookDto changeBookTitle(Long number, String title) throws Exception;

    void deleteBook(Long number) throws Exception;
}

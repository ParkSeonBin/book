package com.example.restprac.service.impl;

import com.example.restprac.data.dto.BookDto;
import com.example.restprac.data.entity.Book;
import com.example.restprac.data.repository.BookRepository;
import com.example.restprac.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getBookId(Long number) { //number을 토대로 검색하여 데이터 조회
        Book book = bookRepository.findById(number).get();

        BookDto bookGetDto = new BookDto();
        bookGetDto.setNumber(book.getNumber());
        bookGetDto.setTitle(book.getTitle());
        bookGetDto.setAuthor(book.getAuthor());
        bookGetDto.setPublisher(book.getPublisher());

        return bookGetDto;
    }

    @Override
    public Optional<Book> getBookByTitle(String title) { //title을 토대로 검색하여 데이터 조회
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getAllBook() { //전체 데이터 검색
        return bookRepository.findAll();
    }

    @Override
    public BookDto saveBook(BookDto bookDto) { //새로운 데이터 저장
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());

        Book savedBook = bookRepository.save(book);

        BookDto bookSaveDto = new BookDto();
        bookSaveDto.setNumber(savedBook.getNumber());
        bookSaveDto.setTitle(savedBook.getTitle());
        bookSaveDto.setAuthor(savedBook.getAuthor());
        bookSaveDto.setPublisher(savedBook.getPublisher());

        return bookSaveDto;
    }

    @Override
    public BookDto changeBookTitle(Long number, String title) throws Exception {
        Book foundBook = bookRepository.findById(number).get();
        foundBook.setTitle(title);
        Book changedBook = bookRepository.save(foundBook);

        BookDto bookChangeDto = new BookDto();
        bookChangeDto.setNumber(changedBook.getNumber());
        bookChangeDto.setTitle(changedBook.getTitle());
        bookChangeDto.setAuthor(changedBook.getAuthor());
        bookChangeDto.setPublisher(changedBook.getPublisher());

        return bookChangeDto;
    }

    @Override
    public void deleteBook(Long number) throws Exception {
        bookRepository.deleteById(number);
    }
}

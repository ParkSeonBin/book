package com.example.restprac.controller;

import com.example.restprac.data.dto.BookDto;
import com.example.restprac.data.dto.ChangeBookTitleDto;
import com.example.restprac.data.entity.Book;
import com.example.restprac.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/find") //조회
    public ResponseEntity<BookDto> getBook(Long number) {
        BookDto bookGetDto = bookService.getBookId(number);

        return ResponseEntity.status(HttpStatus.OK).body(bookGetDto);
    }

    @GetMapping("/findall") //전체 조회
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/findtitle") //title로 조회
    public Optional<Book> getBookByTitle(String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping() //추가
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto bookCreateDto = bookService.saveBook(bookDto);

        return ResponseEntity.status(HttpStatus.OK).body(bookCreateDto);
    }

    @PutMapping() //업데이트
    public ResponseEntity<BookDto> changeBookName(
            @RequestBody ChangeBookTitleDto changeBookTitleDto) throws Exception {

        BookDto bookDto = bookService.changeBookTitle(
                changeBookTitleDto.getNumber(), changeBookTitleDto.getTitle());

        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteBook(Long number) throws Exception {
        bookService.deleteBook(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}

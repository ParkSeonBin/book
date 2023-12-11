package com.example.restprac.controller;

import com.example.restprac.data.dto.BookDto;
import com.example.restprac.data.entity.Book;
import com.example.restprac.data.repository.BookRepository;
import com.example.restprac.service.impl.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // BookController에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    @MockBean
    BookServiceImpl bookService;

    @Test
    @DisplayName("getBookById")
    void getBookIdTest() throws Exception {

        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(bookService.getBookId(123L)).willReturn(
                new BookDto(123L, "spring", "psb", "open"));

        String productId = "123";

        // andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        mockMvc.perform(
                        get("/book/find?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.number").exists()) // json path의 depth가 깊어지면 .을 추가하여 탐색할 수 있음 (ex : $.productId.productIdName)
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.publisher").exists())
                .andDo(print());

        // verify : 해당 객체의 메소드가 실행되었는지 체크해줌
        verify(bookService).getBookId(123L);
    }

    @Test
    @DisplayName("getAllBook")
    void getAllBookTest() throws Exception {
        List<Book> members = List.of(Book.builder().number(1L).title("김길동1").author("김길동1").publisher("김길동1").build()
                , Book.builder().number(2L).title("김길동2").author("김길동2").publisher("김길동2").build()
                , Book.builder().number(3L).title("김길동3").author("김길동3").publisher("김길동3").build()
                , Book.builder().number(4L).title("김길동4").author("김길동4").publisher("김길동4").build());
        given(bookService.getAllBook()).willReturn(members);
        //when
        mockMvc.perform(get("/book/findall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("김길동1"))
                .andExpect(jsonPath("$.[1].title").value("김길동2"))
                .andExpect(jsonPath("$.[2].title").value("김길동3"))
                .andExpect(jsonPath("$.[3].title").value("김길동4"));
    }

    @Test
    @DisplayName("getBookByTitle")
    void getTitleBookTest() throws Exception {



    }
}

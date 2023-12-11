package com.example.restprac.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDto {
    private Long number;
    private String title;
    private String author;
    private String publisher;
}

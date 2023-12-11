package com.example.restprac.data.dto;

public class ChangeBookTitleDto {
    private Long number;
    private String title;

    public ChangeBookTitleDto(Long number, String title) {
        this.number = number;
        this.title = title;
    }

    public ChangeBookTitleDto() {
    }

    public Long getNumber() {
        return this.number;
    }

    public String getTitle() {
        return this.title;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}

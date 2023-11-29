package com.test.rmsoft.book.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookResponseDto {

    private String bookName;
    private String bookDesc;
    private String author;
    private String bookState;

    //대출 횟수
    private int rentCount;

    @Builder

    public BookResponseDto(String bookName, String bookDesc, String author, String bookState, int rentCount) {
        this.bookName = bookName;
        this.bookDesc = bookDesc;
        this.author = author;
        this.bookState = bookState;
        this.rentCount = rentCount;
    }
}

package com.test.rmsoft.book.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BookRegistDto {

    @NotBlank(message = "도서명을 입력해주세요.")
    private String bookName;

    @NotBlank(message = "도서를 설명하는 내용을 입력해주세요.")
    private String bookDesc;

    @NotBlank(message = "저자를 입력해주세요.")
    private String author;

}

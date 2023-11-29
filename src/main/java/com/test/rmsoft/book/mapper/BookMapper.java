package com.test.rmsoft.book.mapper;

import com.test.rmsoft.book.dto.BookRegistDto;
import com.test.rmsoft.book.dto.BookResponseDto;
import com.test.rmsoft.book.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book bookRegistDtoToBook(BookRegistDto bookRegistDto){
        return Book.builder()
                .bookName(bookRegistDto.getBookName())
                .bookDesc(bookRegistDto.getBookDesc())
                .author(bookRegistDto.getAuthor())
                .build();
    }

    public BookResponseDto bookToBookResponseDto(Book book){
        int rentCount = book.getRents().size();
        return BookResponseDto.builder()
                .bookName(book.getBookName())
                .bookDesc(book.getBookDesc())
                .author(book.getAuthor())
                .bookState(book.getBookState().getBookStateDescription())
                .rentCount(rentCount)
                .build();
    }
}

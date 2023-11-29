package com.test.rmsoft.book.controller;

import com.test.rmsoft.book.dto.BookRegistDto;
import com.test.rmsoft.book.dto.BookResponseDto;
import com.test.rmsoft.book.entity.Book;
import com.test.rmsoft.book.mapper.BookMapper;
import com.test.rmsoft.book.service.BookService;
import com.test.rmsoft.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> bookRegister(@Valid @RequestBody BookRegistDto bookRegistDto){
        Book book = bookMapper.bookRegistDtoToBook(bookRegistDto);
        bookService.bookRegister(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{book-id}")
    public ResponseEntity<Void> bookEdit(@Valid @RequestBody BookRegistDto bookRegistDto,
                                         @Positive @PathVariable("book-id") Long bookId){
        Book book = bookMapper.bookRegistDtoToBook(bookRegistDto);
        bookService.bookEdit(book, bookId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponseDto> getBookInfo(@Positive @PathVariable("book-id") Long bookId){
        Book findBook = bookService.getBookInfo(bookId);
        BookResponseDto bookResponseDto = bookMapper.bookToBookResponseDto(findBook);
        return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
    }

}

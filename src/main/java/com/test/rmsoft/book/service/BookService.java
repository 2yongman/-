package com.test.rmsoft.book.service;

import com.test.rmsoft.book.entity.Book;
import org.springframework.security.core.Authentication;

public interface BookService {
    void bookRegister(Book book);

    void bookEdit(Book book, Long bookId);

    void rentCheck(Book book);

    void updateBook(Book book);

    Book getBookInfo(Long bookId);
}

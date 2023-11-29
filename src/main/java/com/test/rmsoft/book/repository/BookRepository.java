package com.test.rmsoft.book.repository;

import com.test.rmsoft.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

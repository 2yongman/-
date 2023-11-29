package com.test.rmsoft.rent.service;

import com.test.rmsoft.rent.entity.Rent;

import java.util.List;

public interface RentService {
    void rentBook(Long bookId);

    List<Rent> rentInfo(Long bookId);

    void returnBook(Long bookId);
}

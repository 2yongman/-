package com.test.rmsoft.rent.controller;

import com.test.rmsoft.rent.dto.RentInfo;
import com.test.rmsoft.rent.entity.Rent;
import com.test.rmsoft.rent.mapper.RentMapper;
import com.test.rmsoft.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/rent")
@RestController
public class RentController {

    private final RentMapper rentMapper;
    private final RentService rentService;

    // 대출
    @PostMapping("/{book-id}")
    public ResponseEntity<String> rentBook(@Positive @PathVariable("book-id") Long bookId){
        rentService.rentBook(bookId);
        return new ResponseEntity<>("대여 성공",HttpStatus.OK);
    }

    //등록한 도서에 대한 대출 이력 확인
    @GetMapping("/{book-id}")
    public ResponseEntity<RentInfo> rentInfo(@Positive @PathVariable("book-id") Long bookId){
        List<Rent> rents = rentService.rentInfo(bookId);
        RentInfo rentInfo = rentMapper.rentsToRentInfo(rents);
        return new ResponseEntity<>(rentInfo,HttpStatus.OK);
    }

    //반납
    @PatchMapping("/{book-id}")
    public ResponseEntity<String> returnBook(@Positive @PathVariable("book-id") Long bookId){
        rentService.returnBook(bookId);
        return new ResponseEntity<>("반납 완료", HttpStatus.ACCEPTED);
    }
}



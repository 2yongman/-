package com.test.rmsoft.rent.service;

import com.test.rmsoft.book.entity.Book;
import com.test.rmsoft.book.service.BookService;
import com.test.rmsoft.exception.BusinessLogicException;
import com.test.rmsoft.exception.ExceptionCode;
import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.member.service.MemberService;
import com.test.rmsoft.rent.entity.Rent;
import com.test.rmsoft.rent.repository.RentRepository;
import com.test.rmsoft.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class RentServiceImpl implements RentService {

    private final BookService bookService;
    private final MemberService memberService;
    private final RentRepository rentRepository;

    @Override
    public void rentBook(Long bookId) {
        Book book = bookService.getBookInfo(bookId);
        //책 상태가 대여 가능인지 확인
        bookService.rentCheck(book);

        String email = AuthUtil.getCurrentMemberEmail();
        Member rentMember = memberService.getMember(email);

        //책을 등록한 사람 본인이 본인 책을 대여할 수 없다.
        checkSelfBorrowing(rentMember,book);

        Rent rent = Rent.builder()
                .rentDate(LocalDate.now())
                .book(book)
                .member(rentMember)
                .build();
        rentRepository.save(rent);

        book.setBookState(Book.BookState.RENT_IMPOSSIBLE);
        bookService.updateBook(book);
    }

    @Override
    public List<Rent> rentInfo(Long bookId) {
        Book book = bookService.getBookInfo(bookId);
        return book.getRents();
    }

    @Override
    public void returnBook(Long bookId) {
        Book findBook = bookService.getBookInfo(bookId);

        String email = AuthUtil.getCurrentMemberEmail();
        Member findMeber = memberService.getMember(email);

        // 반납하려는 사람의 정보와 '미반납'인 대여 기록을 찾으면 되겠다.
        Optional<Rent> optionalRent = rentRepository.findByMemberAndRentState(findMeber, Rent.RentState.OVERDUE);

        if (optionalRent.isPresent()) {
            Rent rentToReturn = optionalRent.get();
            rentToReturn.setRentState(Rent.RentState.RETURN);
            rentRepository.save(rentToReturn);

            findBook.setBookState(Book.BookState.RENT_POSSIBLE);
            bookService.updateBook(findBook);
        }else {
            throw new BusinessLogicException(ExceptionCode.MEMBER_HAS_NO_OVERDUE_RENT);
        }
    }

    private void checkSelfBorrowing(Member rentMember, Book book){
        if (rentMember.getMemberId().equals(book.getMember().getMemberId())){
            throw new BusinessLogicException(ExceptionCode.DO_NOT_BORROWING_SELF);
        }
    }
}

package com.test.rmsoft.book.service;

import com.test.rmsoft.book.entity.Book;
import com.test.rmsoft.book.repository.BookRepository;
import com.test.rmsoft.exception.BusinessLogicException;
import com.test.rmsoft.exception.ExceptionCode;
import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.member.service.MemberService;
import com.test.rmsoft.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final MemberService memberService;

    @Override
    public void bookRegister(Book book) {
        String email = AuthUtil.getCurrentMemberEmail();
        Member member = memberService.getMember(email);
        book.setMember(member);

        bookRepository.save(book);
    }

    @Override
    public void bookEdit(Book book, Long bookId) {
        String email = AuthUtil.getCurrentMemberEmail();
        Member member = memberService.getMember(email);
        Book findBook = this.existCheckBook(bookId);
        this.checkRegisterBookMember(member, findBook);

        Optional.ofNullable(book.getBookName())
                .ifPresent(bookName -> findBook.setBookName(bookName));
        Optional.ofNullable(book.getBookDesc())
                .ifPresent(bookDesc -> findBook.setBookDesc(bookDesc));
        Optional.ofNullable(book.getAuthor())
                .ifPresent(author -> findBook.setAuthor(author));

        bookRepository.save(findBook);
    }

    @Override
    public Book getBookInfo(Long bookId) {
        return existCheckBook(bookId);
    }

    @Override
    public void rentCheck(Book book) {
        if (!book.getBookState().getBookStateDescription().equals("대여 가능")){
            throw new BusinessLogicException(ExceptionCode.IMPOSSIBLE_RENT_BOOK);
        }
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    private Book existCheckBook(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND));
    }

    private void checkRegisterBookMember(Member member, Book book){
        if (!member.getMemberId().equals(book.getMember().getMemberId())){
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        }
    }
}

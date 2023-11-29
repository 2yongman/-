package com.test.rmsoft.book.entity;

import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.rent.entity.Rent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOOK")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Column(name = "BOOK_DESC")
    private String bookDesc;

    @Enumerated(EnumType.STRING)
    private BookState bookState = BookState.RENT_POSSIBLE;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    public enum BookState {
        RENT_POSSIBLE("대여 가능"),
        RENT_IMPOSSIBLE("대여 불가능");

        @Getter
        private String bookStateDescription;

        BookState(String bookStateDescription) {
            this.bookStateDescription = bookStateDescription;
        }
    }

    @Builder
    public Book(String bookName, String bookDesc, String author) {
        this.bookName = bookName;
        this.bookDesc = bookDesc;
        this.author = author;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public void setBookDesc(String bookDesc){
        this.bookDesc = bookDesc;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setBookState(BookState bookState){
        this.bookState = bookState;
    }
}

package com.test.rmsoft.rent.entity;

import com.test.rmsoft.book.entity.Book;
import com.test.rmsoft.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RENT")
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENT_ID")
    private Long rentId;

    @Column(name = "RENT_DATE")
    private LocalDate rentDate;

    @Enumerated(EnumType.STRING)
    private RentState rentState = RentState.OVERDUE;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public enum RentState {
        OVERDUE("미반납"),
        RETURN("반납");

        @Getter
        private String rentStateDescription;

        RentState(String rentStateDescription) {
            this.rentStateDescription = rentStateDescription;
        }
    }

    @Builder
    public Rent(LocalDate rentDate, Book book, Member member) {
        this.rentDate = rentDate;
        this.book = book;
        this.member = member;
    }

    public void setRentState(RentState rentState){
        this.rentState = rentState;
    }
}

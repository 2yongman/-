package com.test.rmsoft.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),

    MEMBER_EXIST(404, "존재하는 이메일입니다."),

    INVALID_MEMBER(404,"책을 등록한 유저가 아닙니다."),

    BOOK_NOT_FOUND(404, "책을 찾을 수 없습니다."),

    IMPOSSIBLE_RENT_BOOK(404, "이미 대여중인 책입니다."),

    DO_NOT_BORROWING_SELF(404, "본인이 등록한 책은 빌릴 수 없습니다."),

    MEMBER_HAS_NO_OVERDUE_RENT(404,"미반납인 대여 기록이 없는 회원입니다.")
    ;

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}

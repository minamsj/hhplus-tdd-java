package io.hhplus.tdd.exception;

public class UserPointNotFoundException extends RuntimeException {
    public UserPointNotFoundException(String message) {
        super(message);
    }

    public UserPointNotFoundException() {
        super("포인트 조회에 실패하였습니다.");
    }
}
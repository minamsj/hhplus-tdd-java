package io.hhplus.tdd.exception;

public class UserPointHistoryNotFoundException extends RuntimeException {
    public UserPointHistoryNotFoundException(String message) {
        super(message);
    }

    public UserPointHistoryNotFoundException() {
        super("포인트 내역 조회에 실패하였습니다.");
    }
}

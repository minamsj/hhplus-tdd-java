package io.hhplus.tdd.exception;

public class ExceedUserPointException extends RuntimeException {
    public ExceedUserPointException(String message) {
        super(message);
    }

    public ExceedUserPointException() {
        super("보유한 포인트보다 많이 사용할 수 없습니다.");
    }
}

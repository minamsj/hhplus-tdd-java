package io.hhplus.tdd.exception;

public class ExceedMaxPointException extends RuntimeException {
    public ExceedMaxPointException(String message) {
        super(message);
    }

    public ExceedMaxPointException() {
        super("10,000,000포인트 이상 보유할 수 없습니다.");
    }
}

package io.hhplus.tdd.exception;

public class InvalidUseAmountException extends RuntimeException {
    public InvalidUseAmountException(String message) {
        super(message);
    }

    public InvalidUseAmountException() {
        super("0포인트 미만은 사용할 수 없습니다.");
    }
}

package io.hhplus.tdd.exception;

public class InvalidChargeAmountException extends RuntimeException {
    public InvalidChargeAmountException(String message) {
        super(message);
    }

    public InvalidChargeAmountException() {
        super("0원 또는 그 이하는 충전할 수 없습니다.");
    }
}

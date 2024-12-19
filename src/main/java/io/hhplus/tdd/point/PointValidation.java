package io.hhplus.tdd.point;

import io.hhplus.tdd.exception.*;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class PointValidation {
    public static void userPointValidation(UserPoint userPoint) {
        if (ObjectUtils.isEmpty(userPoint)) throw new UserPointNotFoundException("포인트 조회에 실패하였습니다.");
    }

    public static void userPointHistoryValidation(List<PointHistory> history) {
        if (ObjectUtils.isEmpty(history)) throw new UserPointHistoryNotFoundException("포인트 내역 조회에 실패하였습니다.");
    }

    public static void chargeValidation(long amount, long userPoint) {
        if (amount <= 0) throw new InvalidChargeAmountException("0원 또는 그 이하는 충전할 수 없습니다.");
        if (userPoint + amount > 10000000) throw new ExceedMaxPointException("10,000,000포인트 이상 보유할 수 없습니다.");
    }

    public static void useValidation(long amount, long userPoint) {
        if (amount <= 0) throw new InvalidUseAmountException("0포인트 미만은 사용할 수 없습니다.");
        if (userPoint - amount < 0) throw new ExceedUserPointException("보유한 포인트보다 많이 사용할 수 없습니다.");
    }
}

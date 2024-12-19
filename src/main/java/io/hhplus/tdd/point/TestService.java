package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.exception.ExceedMaxPointException;
import io.hhplus.tdd.exception.ExceedUserPointException;
import io.hhplus.tdd.exception.InvalidChargeAmountException;
import io.hhplus.tdd.exception.InvalidUseAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestService {
    private final UserPointTable userPointTable = Mockito.mock(UserPointTable.class);
    private final PointHistoryTable pointHistoryTable = Mockito.mock(PointHistoryTable.class);
    private PointService pointService;

    @Test
    public void 포인트_충전에_성공한다() {
        long id = 1L;
        long amount = 10000L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, 1000L, System.currentTimeMillis()));
        Mockito.when(userPointTable.insertOrUpdate(id, 1000L + amount)).thenReturn(new UserPoint(id, 1000L + amount, System.currentTimeMillis()));
        UserPoint userPoint = pointService.charge(id, amount);

        Assertions.assertEquals(11000L, userPoint.point());
    }

    @Test
    public void 충전_포인트가_0포인트_이하면_실패한다() {
        long id = 1L;
        long amount = -100L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, 0, System.currentTimeMillis()));

        Assertions.assertThrows(InvalidChargeAmountException.class, () -> {
            pointService.charge(id, amount);
        });
    }

    @Test
    public void 총_보유_포인트와_충전_포인트_합이_10000000을_넘으면_충전에_실패한다() {
        long id = 1L;
        long amount = 20000000L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, amount, System.currentTimeMillis()));

        Assertions.assertThrows(ExceedMaxPointException.class, () -> {
            pointService.charge(id, amount);
        });
    }

    @Test
    public void 포인트_사용에_성공한다() {
        long id = 1L;
        long amount = 1000L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, 1000L, System.currentTimeMillis()));
        Mockito.when(userPointTable.insertOrUpdate(id, amount)).thenReturn(new UserPoint(id, amount, System.currentTimeMillis()));

        UserPoint userPoint = pointService.use(id, amount);

        Assertions.assertEquals(0L, userPoint.point());
    }

    @Test
    public void 사용_포인트가_0미만이면_사용에_실패한다() {
        long id = 1L;
        long amount = -1L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, 1000L, System.currentTimeMillis()));

        Assertions.assertThrows(InvalidUseAmountException.class, () -> {
            pointService.use(id, amount);
        });
    }

    @Test
    public void 사용_포인트가_보유포인트를_초과하면_사용에_실패한다() {
        long id = 1L;
        long amount = 1000000L;

        Mockito.when(userPointTable.selectById(id)).thenReturn(new UserPoint(id, 500000L, System.currentTimeMillis()));

        Assertions.assertThrows(ExceedUserPointException.class, () -> {
            pointService.use(id, amount);
        });
    }

}

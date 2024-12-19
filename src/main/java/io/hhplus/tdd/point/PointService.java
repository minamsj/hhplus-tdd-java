package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final UserPointTable userPointTable;
    private final PointHistoryTable pointHistoryTable;

    UserPoint point(long id) throws RuntimeException {
        UserPoint userPoint = userPointTable.selectById(id);
        PointValidation.userPointValidation(userPoint);

        return userPoint;
    }

    List<PointHistory> history(long id) throws RuntimeException {
        List<PointHistory> history = pointHistoryTable.selectAllByUserId(id);
        PointValidation.userPointHistoryValidation(history);

        return history;
    }

    public UserPoint charge(long id, long amount) throws RuntimeException {
        UserPoint userPoint = userPointTable.selectById(id);
        PointValidation.userPointValidation(userPoint);
        PointValidation.chargeValidation(amount, userPoint.point());

        UserPoint chargeUserPoint = userPointTable.insertOrUpdate(id, userPoint.point() + amount);
        pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());

        return chargeUserPoint;
    }

    public UserPoint use(long id, long amount) throws RuntimeException {
        UserPoint userPoint = userPointTable.selectById(id);
        PointValidation.userPointValidation(userPoint);
        PointValidation.useValidation(amount, userPoint.point());

        UserPoint useUserPoint = userPointTable.insertOrUpdate(id, userPoint.point() - amount);
        pointHistoryTable.insert(id, -amount, TransactionType.CHARGE, System.currentTimeMillis());

        return useUserPoint;
    }
}

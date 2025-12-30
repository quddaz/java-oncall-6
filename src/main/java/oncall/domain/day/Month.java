package oncall.domain.day;

import java.util.List;
import oncall.exception.DomainExceptionMessage;
import oncall.exception.OnCallDomainException;

public enum Month {
    JAN(1, 31, List.of(1)),
    FEB(2, 28, List.of()),
    MAR(3, 31, List.of(1)),
    APR(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUN(6, 30, List.of(6)),
    JUL(7, 31, List.of()),
    AUG(8, 31, List.of(15)),
    SEPT(9, 30, List.of()),
    OCT(10, 31, List.of(3, 9)),
    NOV(11, 30, List.of()),
    DEC(12, 31, List.of(25));

    private final int month;
    private final int maxDay;
    private final List<Integer> holiday;

    Month(int month, int maxDay, List<Integer> holiday) {
        this.month = month;
        this.maxDay = maxDay;
        this.holiday = holiday;
    }

    public static Month from(int month) {
        for (Month m : values()) {
            if (m.month == month) {
                return m;
            }
        }
        throw new OnCallDomainException(DomainExceptionMessage.MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE);
    }

    public int getMonth() {
        return month;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public List<Integer> getHoliday() {
        return holiday;
    }

    public boolean containsHoliday(int day) {
        return holiday.contains(day);
    }
}

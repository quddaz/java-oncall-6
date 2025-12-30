package oncall.domain.day;

import oncall.exception.DomainExceptionMessage;
import oncall.exception.OnCallDomainException;

public enum DayOfWeek {
    Sunday("일", false),
    Monday("월", true),
    Tuesday("화", true),
    Wednesday("수", true),
    Thursday("목", true),
    Friday("금", true),
    Saturday("토", false);

    private final String name;
    private final boolean isWeek;

    DayOfWeek(String name, boolean isWeek) {
        this.name = name;
        this.isWeek = isWeek;
    }

    public static DayOfWeek from(String week) {
        for (DayOfWeek d : values()) {
            if (d.name.equals(week)) {
                return d;
            }
        }
        throw new OnCallDomainException(DomainExceptionMessage.MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE);
    }

    public boolean getIsWorkingDay() {
        return isWeek;
    }

    public String getName() {
        return name;
    }

    public DayOfWeek next() {
        int nextOrdinal = (this.ordinal() + 1) % DayOfWeek.values().length;
        return DayOfWeek.values()[nextOrdinal];
    }
}

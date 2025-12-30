package oncall.dto;

import oncall.domain.day.DayOfWeek;
import oncall.domain.day.Month;

public record MonthAndWeek(
        Month month,
        DayOfWeek dayOfWeek
) {
}

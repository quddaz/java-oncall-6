package oncall.dto;

import java.util.List;
import oncall.domain.day.Month;

public record MonthDTO(
        int month,
        int maxDay,
        List<Integer> holidays
) {
    public static MonthDTO from(Month month){
        return new MonthDTO(month.getMonth(), month.getMaxDay(), month.getHoliday());
    }
}

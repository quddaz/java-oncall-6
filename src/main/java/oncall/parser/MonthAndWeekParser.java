package oncall.parser;

import oncall.domain.day.DayOfWeek;
import oncall.domain.day.Month;
import oncall.dto.MonthAndWeek;
import oncall.exception.DomainExceptionMessage;
import oncall.exception.OnCallDomainException;

public class MonthAndWeekParser {
    private static final String INPUT_SPLIT_FLAG = ",";

    public MonthAndWeek parse(String input) {
        String[] values = input.replace(" ", "").split(INPUT_SPLIT_FLAG);

        validateLength(values.length);
        Month month = parseMonth(values[0]);
        DayOfWeek day = parseDayOfWeek(values[1]);

        return new MonthAndWeek(month, day);
    }

    private void validateLength(int length) {
        if (length != 2) {
            throw new OnCallDomainException(DomainExceptionMessage.MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Month parseMonth(String month) {
        int m;

        try {
            m = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            throw new OnCallDomainException(DomainExceptionMessage.MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE);
        }

        if (m < 1 || m > 12) {
            throw new OnCallDomainException(DomainExceptionMessage.MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE);
        }
        return Month.from(m);
    }

    private DayOfWeek parseDayOfWeek(String week) {
        return DayOfWeek.from(week);
    }
}

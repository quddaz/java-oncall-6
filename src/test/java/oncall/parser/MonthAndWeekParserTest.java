package oncall.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.dto.MonthAndWeek;
import oncall.exception.OnCallDomainException;
import org.junit.jupiter.api.Test;

public class MonthAndWeekParserTest {

    private final MonthAndWeekParser monthAndWeekParser = new MonthAndWeekParser();

    @Test
    void 정상_입력_반환() {
        //given
        String input = "1,금";

        //when
        MonthAndWeek m = monthAndWeekParser.parse(input);

        //then
        assertThat(m.month().getMonth()).isEqualTo(1);
        assertThat(m.dayOfWeek().getName()).isEqualTo("금");
    }

    @Test
    void 숫자_파싱_예외() {
        //given
        String input = "금,금";

        //when & then
        assertThatThrownBy(() -> monthAndWeekParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 숫자_범위_예외() {
        //given
        String input = "13,금";
        String input2 = "0,금";

        //when & then
        assertThatThrownBy(() -> monthAndWeekParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
        assertThatThrownBy(() -> monthAndWeekParser.parse(input2))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 없는_요일_예외() {
        //given & when
        String input = "11,용";

        //then
        assertThatThrownBy(() -> monthAndWeekParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 잘못된_입력_예외() {
        //given & when
        String input = "11,용";

        //then
        assertThatThrownBy(() -> monthAndWeekParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

}

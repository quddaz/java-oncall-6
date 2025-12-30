package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.domain.day.DayOfWeek;
import oncall.exception.OnCallDomainException;
import org.junit.jupiter.api.Test;

public class DayOfWeekTest {

    @Test
    void 요일_입력_정상_반환_테스트() {
        //given
        String week = "월";

        //when
        DayOfWeek day = DayOfWeek.from(week);

        //then
        assertThat(day.getName()).isEqualTo(week);
    }

    @Test
    void 요일_입력_예와_반환_테스트() {
        //given
        String week = "웡";

        //when & then
        assertThatThrownBy(() -> DayOfWeek.from(week))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 다음_요일_로직_테스트() {
        //given
        DayOfWeek d1 = DayOfWeek.Monday;
        DayOfWeek d2 = DayOfWeek.Saturday;

        //when
        d1 = d1.next();
        d2 = d2.next();

        //then
        assertThat(d1).isEqualTo(DayOfWeek.Tuesday);
        assertThat(d2).isEqualTo(DayOfWeek.Sunday);
    }
}

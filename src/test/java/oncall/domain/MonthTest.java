package oncall.domain;

import oncall.domain.day.Month;
import oncall.exception.OnCallDomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonthTest {

    @Test
    void 월_찾기_테스트(){
        //given
        int month = 12;

        //when
        Month m = Month.from(12);

        //then
        assertThat(m.getMonth()).isEqualTo(month);
    }

    @Test
    void 월_찾기_예외_테스트(){
        //given
        int month = 13;

        //when & then
        assertThatThrownBy(()-> Month.from(month))
                .isInstanceOf(OnCallDomainException.class);

    }
}

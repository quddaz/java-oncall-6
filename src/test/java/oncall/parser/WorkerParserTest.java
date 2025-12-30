package oncall.parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.exception.OnCallDomainException;
import org.junit.jupiter.api.Test;

public class WorkerParserTest {
    WorkerParser workerParser = new WorkerParser();

    @Test
    void 정상_입력_반환() {
        //given & when & then
        String input = "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브";
        workerParser.parse(input);
    }

    @Test
    void 중복_입력_예외() {
        String input = "오션,오션,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브";

        //then
        assertThatThrownBy(() -> workerParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 인풋이_적은_길이_예외() {
        //given & when
        String input = "병웅,지웅,아웅,가웅";

        //then
        assertThatThrownBy(() -> workerParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 긴_이름_예외() {
        //given & when
        String input = "아리스토텔레스,지웅,아웅,가웅,조웅,이웅";

        //then
        assertThatThrownBy(() -> workerParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

    @Test
    void 짧은_이름_예외() {
        //given & when
        String input = ",지웅,아웅,가웅,조웅,이웅";

        //then
        assertThatThrownBy(() -> workerParser.parse(input))
                .isInstanceOf(OnCallDomainException.class);
    }

}

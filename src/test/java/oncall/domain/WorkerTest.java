package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.work.Worker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkerTest {
    @Test
    void 스왑_체크_테스트(){
        //given
        List<String> li = new ArrayList<>();
        li.add("병웅");
        li.add("빙응");
        Worker worker = new Worker(li);

        //when
        worker.checkSwap("병웅");

        //then
        assertThat(worker.process()).isEqualTo("빙응");
    }

    @Test
    void 스왑_체크_테스트2(){
        //given
        List<String> li = new ArrayList<>();
        li.add("병웅");
        li.add("빙응");
        Worker worker = new Worker(li);

        //when
        worker.checkSwap("빙응");

        //then
        assertThat(worker.process()).isEqualTo("병웅");
    }

    @Test
    void 휴일_판별_테스트(){
        //given
        Worker week = new Worker(List.of("비응","뱅응"));
        Worker holiday = new Worker(List.of("비응","뱅응"));

        //when & then
        assertThat(Worker.selectWorker(true, week, holiday)).isEqualTo(holiday);
    }
}

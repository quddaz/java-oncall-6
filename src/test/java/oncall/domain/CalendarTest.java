package oncall.domain;

import oncall.domain.day.DayOfWeek;
import oncall.domain.work.Worker;
import oncall.dto.MonthDTO;
import oncall.dto.SchedulerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarTest {

    private Worker weekWorker;
    private Worker holidayWorker;
    private Calendar calendar;

    @BeforeEach
    void setUp() {
        weekWorker = new Worker(List.of("빙응", "조이"));
        holidayWorker = new Worker(List.of("자이"));

        // 테스트용 MonthDTO
        MonthDTO testMonth = new MonthDTO(
                5,                  // 5월
                5,                  // 5일만 테스트
                List.of(1, 3)       // 1일, 3일이 휴일
        );

        calendar = new Calendar(testMonth, DayOfWeek.Monday);
    }

    @Test
    void 스케줄_생성_테스트() {
        //When
        List<SchedulerDTO> scheduler = calendar.getScheduler(weekWorker, holidayWorker);

        //Then
        assertThat(scheduler).hasSize(5);

        //Then
        assertThat(scheduler.get(0).isWeek()).isTrue();
        assertThat(scheduler.get(1).isWeek()).isFalse();
        assertThat(scheduler.get(2).isWeek()).isTrue();
        assertThat(scheduler.get(3).isWeek()).isFalse();
        assertThat(scheduler.get(4).isWeek()).isFalse();

        assertThat(scheduler.get(0).workerName()).isEqualTo("자이");
        assertThat(scheduler.get(1).workerName()).isIn("빙응", "조이");
    }
}

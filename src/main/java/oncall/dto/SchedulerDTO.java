package oncall.dto;

import oncall.domain.work.Worker;

public record SchedulerDTO(
        int month,
        int day,
        String weekName,
        String workerName,
        boolean isWeek
) {
}

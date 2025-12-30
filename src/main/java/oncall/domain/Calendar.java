package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.day.DayOfWeek;
import oncall.domain.work.Worker;
import oncall.dto.MonthDTO;
import oncall.dto.SchedulerDTO;
import oncall.dto.WorkDTO;

public class Calendar {
    private final List<WorkDTO> isHoliday;
    private final MonthDTO month;

    public Calendar(MonthDTO month, DayOfWeek startWeek) {
        this.isHoliday = new ArrayList<>();
        this.month = month;
        init(startWeek);
    }

    private void init(DayOfWeek startWeek) {
        DayOfWeek day = startWeek;

        for (int data = 1; data <= month.maxDay(); data++) {
            boolean week = isHoliday(day, data);
            isHoliday.add(new WorkDTO(week, day.getName()));
            day = day.next();
        }
    }

    private boolean isHoliday(DayOfWeek now, int data) {
        if (!now.getIsWorkingDay()) {
            return true;
        }

        return month.holidays().contains(data);
    }

    public List<SchedulerDTO> getScheduler(Worker weekWorker, Worker holidayWorker) {
        List<SchedulerDTO> scheduler = new ArrayList<>();
        String lastWorkerName = "";

        for (int i = 0; i < isHoliday.size(); i++) {
            scheduler.add(createSchedulerForDay(i, lastWorkerName, weekWorker, holidayWorker));
            lastWorkerName = scheduler.get(i).workerName();
        }

        return scheduler;
    }

    private SchedulerDTO createSchedulerForDay(int dayIndex, String lastWorkerName,
                                               Worker weekWorker, Worker holidayWorker) {
        WorkDTO day = isHoliday.get(dayIndex);
        Worker currentWorker = Worker.selectWorker(day.isHoliday(), weekWorker, holidayWorker);

        currentWorker.checkSwap(lastWorkerName);
        String todayWorker = currentWorker.process();

        return new SchedulerDTO(
                month.month(),
                dayIndex + 1,
                day.weekName(),
                todayWorker,
                month.holidays().contains(dayIndex + 1)
        );
    }

}

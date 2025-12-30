package oncall.view;


import oncall.dto.SchedulerDTO;

public class OutputView {
    public void printMonthWeekInputMessage() {
        System.out.println(OutputConfig.MONTH_AND_WEEK_INPUT_MESSAGE.getFormat());
    }

    public void printWeekWorkerInputMessage() {
        System.out.println(OutputConfig.WEEK_WORKER_INPUT_MESSAGE.getFormat());
    }

    public void printHolidayWorkerInputMessage() {
        System.out.println(OutputConfig.HOLIDAY_WORKER_INPUT_MESSAGE.getFormat());
    }

    public void printWorkingScheduler(SchedulerDTO schedulerDTO) {
        String format = OutputConfig.WEEKDAY_OUTPUT_MESSAGE.getFormat();

        if (schedulerDTO.isWeek()) {
            format = OutputConfig.PUBLIC_HOLIDAY_OUTPUT_MESSAGE.getFormat();
        }

        System.out.printf(
                (format) + "%n",
                schedulerDTO.month(),
                schedulerDTO.day(),
                schedulerDTO.weekName(),
                schedulerDTO.workerName()
        );
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
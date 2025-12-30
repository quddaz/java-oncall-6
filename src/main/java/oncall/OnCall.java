package oncall;

import java.util.List;
import oncall.domain.Calendar;
import oncall.domain.work.Worker;
import oncall.dto.MonthAndWeek;
import oncall.dto.MonthDTO;
import oncall.dto.SchedulerDTO;
import oncall.exception.OnCallDomainException;
import oncall.parser.MonthAndWeekParser;
import oncall.parser.WorkerParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCall {
    private final InputView inputView;
    private final OutputView outputView;
    private final MonthAndWeekParser monthAndWeekParser;
    private final WorkerParser workerParser;

    public OnCall(InputView inputView, OutputView outputView, MonthAndWeekParser monthAndWeekParser,
                  WorkerParser workerParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.monthAndWeekParser = monthAndWeekParser;
        this.workerParser = workerParser;
    }

    public void run() {
        outputView.printMonthWeekInputMessage();
        MonthAndWeek monthAndWeek = inputMonthAndWeek();

        outputView.printWeekWorkerInputMessage();
        Worker weekWorker = new Worker(inputWorker());

        outputView.printHolidayWorkerInputMessage();
        Worker holidayWorker = new Worker(inputWorker());

        Calendar calendar = new Calendar(
                MonthDTO.from(monthAndWeek.month()),
                monthAndWeek.dayOfWeek());
        printResult(calendar.getScheduler(weekWorker, holidayWorker));
    }

    private MonthAndWeek inputMonthAndWeek() {
        while (true) {
            try {
                String input = inputView.readInput();
                return monthAndWeekParser.parse(input);
            } catch (OnCallDomainException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private List<String> inputWorker() {
        while (true) {
            try {
                String input = inputView.readInput();
                return workerParser.parse(input);
            } catch (OnCallDomainException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void printResult(List<SchedulerDTO> li) {
        for (SchedulerDTO s : li) {
            outputView.printWorkingScheduler(s);
        }
    }
}

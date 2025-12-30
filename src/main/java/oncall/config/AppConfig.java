package oncall.config;

import oncall.OnCall;
import oncall.parser.MonthAndWeekParser;
import oncall.parser.WorkerParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {
    private final InputView inputView;
    private final OutputView outputView;
    private final MonthAndWeekParser monthAndWeekParser;
    private final WorkerParser workerParser;

    private final OnCall onCall;

    public AppConfig() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.monthAndWeekParser = new MonthAndWeekParser();
        this.workerParser = new WorkerParser();
        this.onCall = new OnCall(inputView, outputView, monthAndWeekParser, workerParser);
    }

    public OnCall getOnCall() {
        return onCall;
    }
}

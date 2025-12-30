package oncall.view;


public enum OutputConfig {
    MONTH_AND_WEEK_INPUT_MESSAGE("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    WEEK_WORKER_INPUT_MESSAGE("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    HOLIDAY_WORKER_INPUT_MESSAGE("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    WEEKDAY_OUTPUT_MESSAGE("%d월 %d일 %s %s"),
    PUBLIC_HOLIDAY_OUTPUT_MESSAGE("%d월 %d일(휴일) %s %s");


    private final String format;

    OutputConfig(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
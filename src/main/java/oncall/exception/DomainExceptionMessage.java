package oncall.exception;

public enum DomainExceptionMessage {
    MONTH_AND_WEEK_INPUT_EXCEPTION_MESSAGE("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    WORKER_INPUT_EXCEPTION_MESSAGE("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    private final String message;

    DomainExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
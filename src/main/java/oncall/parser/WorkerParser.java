package oncall.parser;

import java.util.Arrays;
import java.util.List;
import oncall.exception.DomainExceptionMessage;
import oncall.exception.OnCallDomainException;

public class WorkerParser {
    private static final String INPUT_SPLIT_FLAG = ",";
    private static final int WORKER_MIN_COUNT = 5;
    private static final int WORKER_MAX_COUNT = 35;


    public List<String> parse(String input) {
        String[] values = input.replace(" ", "").split(INPUT_SPLIT_FLAG);

        validate(values);

        return Arrays.stream(values).toList();
    }

    private void validate(String[] values) {
        long distinctCount = Arrays.stream(values).distinct().count();
        validateDuplicate(values, distinctCount);
        validateRange(distinctCount);
        validateNameLength(values);
    }

    private void validateDuplicate(String[] values, long count) {
        if (count != values.length) {
            throw new OnCallDomainException(DomainExceptionMessage.WORKER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private void validateRange(long count) {
        if (count < WORKER_MIN_COUNT || count > WORKER_MAX_COUNT) {
            throw new OnCallDomainException(DomainExceptionMessage.WORKER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private void validateNameLength(String[] values) {
        boolean invalid = Arrays.stream(values).anyMatch(a -> a.length() > 5 || a.isEmpty());

        if (invalid) {
            throw new OnCallDomainException(DomainExceptionMessage.WORKER_INPUT_EXCEPTION_MESSAGE);
        }
    }
}

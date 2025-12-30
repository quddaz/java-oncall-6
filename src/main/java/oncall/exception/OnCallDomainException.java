package oncall.exception;

public class OnCallDomainException extends RuntimeException {
    public OnCallDomainException(DomainExceptionMessage message) {
        super(message.message());
    }
}

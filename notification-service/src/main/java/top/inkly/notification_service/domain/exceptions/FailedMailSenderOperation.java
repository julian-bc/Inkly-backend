package top.inkly.notification_service.domain.exceptions;

public class FailedMailSenderOperation extends RuntimeException {
    public FailedMailSenderOperation(String message) {
        super(message);
    }
}

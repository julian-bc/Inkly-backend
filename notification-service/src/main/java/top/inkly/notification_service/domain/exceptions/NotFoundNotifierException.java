package top.inkly.notification_service.domain.exceptions;

public class NotFoundNotifierException extends RuntimeException {
    public NotFoundNotifierException(String message) {
        super(message);
    }
}

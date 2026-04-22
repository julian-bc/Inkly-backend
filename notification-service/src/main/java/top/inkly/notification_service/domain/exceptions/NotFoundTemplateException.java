package top.inkly.notification_service.domain.exceptions;

public class NotFoundTemplateException extends RuntimeException{
    public NotFoundTemplateException(String message) {
        super(message);
    }
}

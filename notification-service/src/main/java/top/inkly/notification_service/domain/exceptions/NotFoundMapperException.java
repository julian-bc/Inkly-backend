package top.inkly.notification_service.domain.exceptions;

public class NotFoundMapperException extends RuntimeException{
    public NotFoundMapperException(String message) {
        super(message);
    }
}

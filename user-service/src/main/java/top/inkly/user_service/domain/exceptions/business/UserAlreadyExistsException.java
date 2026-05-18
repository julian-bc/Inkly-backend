package top.inkly.user_service.domain.exceptions.business;

public class UserAlreadyExistsException extends RuntimeException {
    public  UserAlreadyExistsException(String message) {
        super(message);
    }
}

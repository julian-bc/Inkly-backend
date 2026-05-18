package top.inkly.user_service.domain.exceptions.auth;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}

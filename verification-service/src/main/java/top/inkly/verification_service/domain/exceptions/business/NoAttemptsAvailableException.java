package top.inkly.verification_service.domain.exceptions.business;

public class NoAttemptsAvailableException extends RuntimeException {
    public NoAttemptsAvailableException(String message) {
        super(message);
    }
}

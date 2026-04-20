package top.inkly.user_service.domain.exceptions.business;

public class FailedDatabaseOperation extends RuntimeException {
    public FailedDatabaseOperation(String message) {
        super(message);
    }
}

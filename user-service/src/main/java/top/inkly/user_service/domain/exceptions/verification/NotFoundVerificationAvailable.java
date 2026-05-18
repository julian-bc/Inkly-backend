package top.inkly.user_service.domain.exceptions.verification;

public class NotFoundVerificationAvailable extends RuntimeException {
    public NotFoundVerificationAvailable(String message) {
        super(message);
    }
}

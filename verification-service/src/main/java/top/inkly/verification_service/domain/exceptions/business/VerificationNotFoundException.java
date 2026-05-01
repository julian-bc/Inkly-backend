package top.inkly.verification_service.domain.exceptions.business;

public class VerificationNotFoundException extends RuntimeException {
    public VerificationNotFoundException(String message) {
        super(message);
    }
}

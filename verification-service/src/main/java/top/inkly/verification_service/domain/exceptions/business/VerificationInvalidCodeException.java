package top.inkly.verification_service.domain.exceptions.business;

public class VerificationInvalidCodeException extends RuntimeException {
    public VerificationInvalidCodeException(String message) {
        super(message);
    }
}

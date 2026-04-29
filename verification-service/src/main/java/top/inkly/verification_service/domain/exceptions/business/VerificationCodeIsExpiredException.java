package top.inkly.verification_service.domain.exceptions.business;

public class VerificationCodeIsExpiredException extends RuntimeException {
    public VerificationCodeIsExpiredException(String message) {
        super(message);
    }
}

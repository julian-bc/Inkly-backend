package top.inkly.verification_service.infrastructure.input.rest.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.inkly.verification_service.domain.exceptions.business.NoAttemptsAvailableException;
import top.inkly.verification_service.domain.exceptions.business.VerificationCodeIsExpiredException;
import top.inkly.verification_service.domain.exceptions.business.VerificationNotFoundException;

@RestControllerAdvice
public class VerificationHandler {
    @ExceptionHandler(VerificationNotFoundException.class)
    public ResponseEntity<String> handleVerificationNotFound(VerificationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            VerificationCodeIsExpiredException.class,
            NoAttemptsAvailableException.class
    })
    public ResponseEntity<String> handleVerificationCodeIsExpired(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.GONE);
    }
}

package top.inkly.user_service.infrastructure.input.rest.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.inkly.shared.infrastructure.input.dto.message.ErrorResponse;
import top.inkly.user_service.domain.exceptions.auth.IncorrectCredentialsException;

import java.time.LocalDateTime;

import static top.inkly.shared.infrastructure.input.handler.ErrorHelperBuilder.doDetails;

@RestControllerAdvice
public class AuthHandlerException {

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectCredentialsException(IncorrectCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorResponse.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .details(doDetails(ex))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}

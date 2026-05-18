package top.inkly.shared.infrastructure.input.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.inkly.shared.infrastructure.input.dto.message.ErrorResponse;

import java.time.LocalDateTime;

import static top.inkly.shared.infrastructure.input.handler.ErrorHelperBuilder.doDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .details(doDetails(ex))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}

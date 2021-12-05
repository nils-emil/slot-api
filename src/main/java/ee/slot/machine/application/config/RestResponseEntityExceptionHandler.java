package ee.slot.machine.application.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseEntity.status(400)
                .body(Error
                        .builder()
                        .reason("Input validation error")
                        .build());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.status(400)
                .body(Error
                        .builder()
                        .reason(ex.getMessage())
                        .build());
    }

}
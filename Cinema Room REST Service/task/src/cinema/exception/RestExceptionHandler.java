package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyPurchasedException.class)
    protected ResponseEntity<Object> handleAlreadyPurchased(AlreadyPurchasedException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(SeatOutOfBoundsException.class)
    protected ResponseEntity<Object> handleAlreadyPurchased(SeatOutOfBoundsException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    protected ResponseEntity<Object> handleWrongToken(WrongTokenException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected ResponseEntity<Object> handleWrongPassport(WrongPasswordException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage());
        return new ResponseEntity<>(apiError, UNAUTHORIZED);
    }

}
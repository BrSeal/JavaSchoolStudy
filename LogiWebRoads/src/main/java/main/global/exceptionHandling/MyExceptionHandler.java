package main.global.exceptionHandling;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import main.global.exceptionHandling.exceptions.RestException;

@ControllerAdvice
@Log4j2
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Marker BAD_REQUEST_MARKER= MarkerManager.getMarker("BAD_REQUEST");
    private static final Marker RUNTIME_ERROR_MARKER= MarkerManager.getMarker("RUNTIME_ERROR");

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<String> handleE(RestException ex) {
     logger.warn(BAD_REQUEST_MARKER,ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<String> handleE(RuntimeException ex) {
        logger.warn(RUNTIME_ERROR_MARKER,ex);
        return new ResponseEntity<>("Something gone completely wrong!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleE(Exception ex) {
        logger.error(ex);
        return new ResponseEntity<>("Nobody hears your screams but you can press any button!", HttpStatus.BAD_REQUEST);
    }
}

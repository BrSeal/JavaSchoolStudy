package main.global.exceptionHandling;

import lombok.extern.log4j.Log4j2;
import main.global.exceptionHandling.exceptions.RestException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Log4j2
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Marker BAD_REQUEST_MARKER= MarkerManager.getMarker("BAD_REQUEST");
    private static final Marker RUNTIME_ERROR_MARKER= MarkerManager.getMarker("RUNTIME_ERROR");
    private static final Marker FATAL_ERROR_MARKER= MarkerManager.getMarker("FATAL_ERROR");

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<String> handleE(RestException ex) {
     log.warn(BAD_REQUEST_MARKER,ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<String> handleE(RuntimeException ex) {
        log.error(RUNTIME_ERROR_MARKER,beautifyStackTrace(ex));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleE(Exception ex) {
        log.error(FATAL_ERROR_MARKER,beautifyStackTrace(ex));
        return new ResponseEntity<>("Nobody hears your screams but you can press any button!", HttpStatus.BAD_REQUEST);
    }

    private String beautifyStackTrace(Throwable ex){
        StringWriter stringWriter=new StringWriter();
        PrintWriter pw=new PrintWriter(stringWriter);
        ex.printStackTrace(pw);
        return ex.getMessage()+'\n'+stringWriter.toString();

    }
}
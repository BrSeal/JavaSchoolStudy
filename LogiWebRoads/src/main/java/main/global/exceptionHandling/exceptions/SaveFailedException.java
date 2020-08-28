package main.global.exceptionHandling.exceptions;

public class SaveFailedException extends RuntimeException {
    public SaveFailedException(String errMsg) {
        super(errMsg);
    }
}

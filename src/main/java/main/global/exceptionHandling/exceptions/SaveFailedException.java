package main.global.exceptionHandling.exceptions;

public class SaveFailedException extends RestException {
    public SaveFailedException(String errMsg) {
        super(errMsg);
    }
}

package main.global.exceptionHandling.exceptions;

public class UpdateFailException extends RestException {
    private static final String UPDATE_FAILED = "Failed to update %s #%d!";

    public UpdateFailException(Class clazz, int id) {
        super(String.format(UPDATE_FAILED, clazz.getSimpleName(), id));
    }

    public UpdateFailException(String errMsg) {
        super(errMsg);
    }
}

package main.global.exceptionHandling.exceptions;

public class DeletionFailedException extends RuntimeException{
    private static final String UPDATE_FAILED="Failed to delete %s #%d!";
    public DeletionFailedException(Class clazz, int id){
        super(String.format(UPDATE_FAILED,clazz.getSimpleName(),id));
    }

    public DeletionFailedException(String errMsg){
        super(errMsg);
    }
}

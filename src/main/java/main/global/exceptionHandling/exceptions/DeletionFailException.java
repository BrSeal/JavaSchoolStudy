package main.global.exceptionHandling.exceptions;

public class DeletionFailException extends RestException{
    private static final String UPDATE_FAILED="Failed to delete %s #%d!";
    public DeletionFailException(Class clazz, int id){
        super(String.format(UPDATE_FAILED,clazz.getSimpleName(),id));
    }

    public DeletionFailException(String errMsg){
        super(errMsg);
    }
}

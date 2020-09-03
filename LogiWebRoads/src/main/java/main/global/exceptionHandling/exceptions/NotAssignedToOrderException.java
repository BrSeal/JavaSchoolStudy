package main.global.exceptionHandling.exceptions;

public class NotAssignedToOrderException extends RestException{
    private static final String NOT_ASSIGNED="%s is not assigned to order #%d!";
    public NotAssignedToOrderException(Class clazz, int id){
        super(String.format(NOT_ASSIGNED,clazz.getSimpleName(),id));
    }

    public NotAssignedToOrderException(String errMsg){
        super(errMsg);
    }
}

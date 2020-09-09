package main.global.exceptionHandling.exceptions;

public class NotFoundException extends RestException{
    private static final String NOT_FOUND="%s #%d not found in database!";
    public NotFoundException(Class clazz, int id){
        super(String.format(NOT_FOUND,clazz.getSimpleName(),id));
    }
    public NotFoundException(Class clazz, String id){
        super(String.format(NOT_FOUND,clazz.getSimpleName(),id));
    }

    public NotFoundException(String errMsg){
        super(errMsg);
    }
}

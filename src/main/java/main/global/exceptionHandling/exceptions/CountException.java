package main.global.exceptionHandling.exceptions;

public class CountException extends RuntimeException{

    private static final String LESS_THAN_ZERO="Counter of %s cant be less than 0!";

    public CountException(String entityName){
        super(String.format(LESS_THAN_ZERO,entityName));
    }
}

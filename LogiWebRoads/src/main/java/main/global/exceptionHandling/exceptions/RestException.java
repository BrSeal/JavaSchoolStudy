package main.global.exceptionHandling.exceptions;

public class RestException extends RuntimeException{
    public RestException(String s){
        super(s);
    }

    public RestException(){
        super();
    }
}

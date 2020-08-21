package main.globalLogic.exceptionHandling.exceptions;

public class DtoConvertForbiddenException extends IllegalArgumentException{
    public DtoConvertForbiddenException(){
        super("This DTO can't be converted to object! It is used to transfer info on the front-end!");
    }

}

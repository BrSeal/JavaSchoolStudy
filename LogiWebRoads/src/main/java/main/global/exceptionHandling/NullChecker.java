package main.global.exceptionHandling;

import main.global.exceptionHandling.exceptions.NotAssignedToOrderException;
import main.global.exceptionHandling.exceptions.NotFoundException;

import java.util.List;
import java.util.Objects;

public class NullChecker {
    private static final String NO_MATCHES_FOUND="No matching %ss found for order #%d";


    //TODO Почему полное имя на выходе класса????
    public void throwNotFoundIfEmptyList(List<? extends Object> objects, Class clazz, int orderId){
        if(objects.isEmpty()) {
            String errMsg=String.format(NO_MATCHES_FOUND,clazz.getSimpleName(),orderId);
            throw new NotFoundException(errMsg);
        }
    }

    public void throwNotFoundIfNull(Object o, String errMsg) {
        if (Objects.isNull(o)) {
            throw new NotFoundException(errMsg);
        }
    }

    public void throwNotFoundIfNull(Object o, Class clazz, int id) {
        if (Objects.isNull(o)) {
            throw new NotFoundException(clazz, id);
        }
    }

    public void throwNotAssignedIfNull(Object o, String errMsg) {
        if (Objects.isNull(o)) {
                    throw new NotAssignedToOrderException(errMsg);
            }
        }

    public void throwNotAssignedIfNull(Object o, Class objectClass, int id) {
        if (Objects.isNull(o)) {
            throw new NotAssignedToOrderException(objectClass, id);
        }
    }
}

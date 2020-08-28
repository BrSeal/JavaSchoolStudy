package main.global.exceptionHandling;

import main.global.exceptionHandling.exceptions.NotAssignedToOrderException;
import main.global.exceptionHandling.exceptions.NotFoundException;

import java.util.Objects;

public class NullChecker {

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

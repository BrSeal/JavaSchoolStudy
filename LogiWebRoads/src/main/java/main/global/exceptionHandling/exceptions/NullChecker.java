package main.global.exceptionHandling.exceptions;

import java.util.Objects;

public class NullChecker {
    public static final String NOT_FOUND = "not found";
    public static final String NOT_ASSIGNED = "not assigned";

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

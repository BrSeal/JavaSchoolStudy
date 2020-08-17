package main.core.driver.services;

import main.model.users.Driver;
import main.model.users.DriverStatus;

public class DriverCheckProvider {
    private static final String DELETE_FAIL = "Deletion failed! Driver #%d is currently on order #%d!";

    public static void canBeDeleted(Driver d) {
        boolean canBeDeleted=  d.getCurrentOrder() == null && d.getStatus().equals(DriverStatus.ON_REST);

        if (!canBeDeleted) {
            int id = d.getId();
            int orderId = d.getCurrentOrder().getId();
            throw new IllegalArgumentException(String.format(DELETE_FAIL, id, orderId));
        }
    }
}

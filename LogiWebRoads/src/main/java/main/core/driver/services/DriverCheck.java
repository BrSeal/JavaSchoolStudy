package main.core.driver.services;

import main.model.users.Driver;
import main.model.users.DriverStatus;

public class DriverCheck {
    public static boolean canBeDeleted(Driver d) {
        return d.getCurrentOrder() == null && d.getStatus().equals(DriverStatus.ON_REST);
    }
}

package main.core.waypoint.services;

import main.model.IdClass;
import main.model.logistic.City;
import main.model.logistic.Waypoint;

import java.util.List;
import java.util.stream.Collectors;

public class WaypointCheckProvider {

    public void cityCheck(List<Waypoint> waypoints, List<City> cities) {
        List<Integer> cityIds=cities.stream().map(IdClass::getId).collect(Collectors.toList());
        waypoints.forEach(w -> {
            if (!cityIds.contains(w.getCity().getId())) throw new IllegalArgumentException("City does not exist!");
        });


    }
}

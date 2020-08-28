package main.core.orderManagement.waypoint.services;

import main.global.mappedSuperclass.IdClass;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.waypoint.entity.Waypoint;

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

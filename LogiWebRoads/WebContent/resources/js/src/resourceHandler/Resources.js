import React from "react";
import orderRepository from "./repositories/OrderRepositoty";
import cityRepository from "./repositories/CityRepository";
import driverRepository from "./repositories/DriverRepository";
import vehicleRepository from "./repositories/VehicleRepository";

class ResourceService {
    constructor() {
        this.drivers = driverRepository.init();
        this.orders = orderRepository.init();
        this.vehicles = vehicleRepository.init();
        this.cargos = this.initCargos();
        this.cities = cityRepository.init();
    }

    updateOrders(){
        this.orders = orderRepository.init();
    }

    updateDrivers(){
        this.drivers = driverRepository.init();
    }

    updateVehicles(){
        this.vehicles = vehicleRepository.init();
    }

    updateCargos(){
        this.cargos = orderRepository.init();
    }

    initCargos() {
        let cargos = new Map();
        $.ajax({
            method: "GET",
            url: '../cargo/',
            success: function (response) {
                response.forEach((item, index) => cargos.set(item.id, item));
            }
        });
        return cargos;
    }
}

const resources = new ResourceService();
export default resources;
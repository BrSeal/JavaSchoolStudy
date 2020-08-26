import React from "react";
import orderRepository from "./repositories/OrderRepositoty";
import cityRepository from "./repositories/CityRepository";
import driverRepository from "./repositories/DriverRepository";
import vehicleRepository from "./repositories/VehicleRepository";

class ResourceService {
    constructor() {
        this.drivers = driverRepository.init();
        this.orders = orderRepository.init();
        this.vehicles = this.initVehicles();
        this.cargos = this.initCargos();
        this.cities = cityRepository.init();
    }

    updateOrders(){
        this.orders = orderRepository.init();
    }

    updateDrivers(){
        this.orders = driverRepository.init();
    }

    updateVehicles(){
        this.orders = vehicleRepository.init();
    }

    updateCargos(){
        this.orders = orderRepository.init();
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

    initVehicles() {
        let vehicles=new Map();
        $.ajax({
            method: "GET",
            url: '../vehicle/',
            success: function (response) {
                response.forEach((item, index) => vehicles.set(item.id, item));
            }
        });
        return vehicles;
    }
}


const resources = new ResourceService();
export default resources;
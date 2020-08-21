import React from "react";

class ResourceService {
    constructor() {
        this.drivers = this.initDrivers();
        this.orders = this.initOrders();
        this.vehicles = this.initVehicles();
        this.cargos = this.initCargos();
        this.cities = this.initCities();
    }

    initCities() {
        let cities = new Map();
        $.get("../city/")
            .done(function (response) {

                response.map((item, index) => cities.set(item.id, item));
            })
            .fail(function (response) {
                console.log(response);
            })
        return cities;
    }

    initDrivers() {
        let drivers= new Map();
        $.ajax({
            method: "GET",
            url: '../driver/',
            success: function (response) {
                response.forEach((item, index) => drivers.set(item.id, item));
            }
        });
        return drivers;
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

    initOrders() {
        let orders=new Map();
        $.ajax({
            method: "GET",
            url: '../order/',
            success: function (response) {
                response.forEach((item, index) => orders.set(item.id, item));
            }
        });
        return orders;
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
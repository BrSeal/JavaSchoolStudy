import React, {Component} from "react";
import {showOrders} from "./employeeDesc/Order/orderLogic";
import {showDrivers} from "./employeeDesc/Driver/driverLogic";
import {showVehicles} from "./employeeDesc/Vehicle/vehicleLogic";
import {showCargos} from "./employeeDesc/Cargo/cargoLogic";

export class MainContainer extends Component{
    render() {
        return (
            <div className="container">

                <div id="buttons" className="btn-group row" role="group">
                    <button id="orders-btn" className="btn btn-info" onClick={showOrders}>Orders</button>
                    <button id="Drivers-btn" className="btn btn-info" onClick={showDrivers}>Drivers</button>
                    <button id="Vehicles-btn" className="btn btn-info" onClick={showVehicles}>Vehicles</button>
                    <button id="Cargos-btn" className="btn btn-info" onClick={showCargos}>Cargos</button>
                </div>
                <div id="add-button-holder" className="row">

                </div>
                <div className="row">
                    <div id="content" className="col-8 p-0">
                    </div>

                    <div id="details" className="col-4">
                    </div>
                </div>
            </div>
        );
    }
}
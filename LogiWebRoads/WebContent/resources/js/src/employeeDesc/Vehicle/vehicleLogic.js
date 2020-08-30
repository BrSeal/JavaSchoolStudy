import React from "react";
import ReactDOM from "react-dom";
import {VehicleTable} from "./VehicleTable";
import VehicleAddButton from "./VehicleAddButton";

export function showVehicles() {
    ReactDOM.render(<VehicleAddButton/>, document.getElementById('add-button-holder'));
    ReactDOM.render(<VehicleTable/>,document.getElementById('content'));
    ReactDOM.render('',document.getElementById('details'));
}
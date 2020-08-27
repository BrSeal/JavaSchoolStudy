import React from "react";
import ReactDOM from "react-dom";
import DriverAddButton from "./DriverAddButton";
import {DriverTable} from "./DriverTable";

export function showDrivers() {
    ReactDOM.render(<DriverAddButton/>, document.getElementById('add-button-holder'));
    ReactDOM.render(<DriverTable/>,document.getElementById('content'));
    ReactDOM.render('',document.getElementById('details'));
}
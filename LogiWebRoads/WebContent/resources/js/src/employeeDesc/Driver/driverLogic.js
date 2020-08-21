import React from "react";
import ReactDOM from "react-dom";
import DriverAddButton from "./DriverAddButton";

export function showDrivers() {
    ReactDOM.render(<DriverAddButton/>, document.getElementById('add-button-holder'));
    ReactDOM.render('',document.getElementById('content'));
    ReactDOM.render('',document.getElementById('details'));
}
import React from "react";
import ReactDOM from "react-dom";
import {CargoTable} from "./CargoTable";

export function showCargos() {
    ReactDOM.render('', document.getElementById('add-button-holder'));
    ReactDOM.render(<CargoTable/>,document.getElementById('content'));
    ReactDOM.render('',document.getElementById('details'));
}
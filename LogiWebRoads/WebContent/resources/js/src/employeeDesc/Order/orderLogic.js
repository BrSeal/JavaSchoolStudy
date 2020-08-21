import React from "react";
import ReactDOM from "react-dom";
import {OrderTable} from "./OrderTable";
import resources from "../../resourceHandler/Resources";
import {OrderAddButton} from "./OrderAddButton";


export function showOrders() {
            ReactDOM.render(<OrderTable orders={resources.orders}/>, document.getElementById('content'));
            ReactDOM.render(<OrderAddButton/>, document.getElementById('add-button-holder'));
            ReactDOM.render('', document.getElementById('details'));
}


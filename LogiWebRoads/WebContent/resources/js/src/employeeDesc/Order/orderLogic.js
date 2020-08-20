import React from "react";
import ReactDOM from "react-dom";
import {OrderTable} from "./OrderTable";

export function showOrders() {
    $.ajax({
        method: "GET",
        url: '../order/',
        success: function (response) {
            ReactDOM.render(<OrderTable orders={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
        }
    })
}


import React, {Component} from "react";
import ReactDOM from "react-dom";
import {OrderForm} from "./OrderForm/OrderForm";


export class OrderAddButton extends Component {
    render() {
        const showForm = function () {
            ReactDOM.render(
                <OrderForm/>,
                document.getElementById('details')
            );
        }
        return (
            <button className='btn btn-sm btn-info' onClick={showForm}>Add order</button>
        );
    }
}
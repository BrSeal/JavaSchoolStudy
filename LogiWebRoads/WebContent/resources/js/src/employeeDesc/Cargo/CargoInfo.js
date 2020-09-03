import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../resourceHandler/Resources";

export class CargoInfo extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const cargo=this.props.cargo;

        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        return (
            <div>
                <h1>Cargo #{cargo.id}</h1>
                <div><b>Name:</b> {cargo.name}</div>
                <div><b>Weight:</b> {cargo.weight}</div>
                <div><b>Status:</b> {cargo.status}</div>
                <div><b>From:</b> {resources.cities.get(cargo.cityIdFrom).name}</div>
                <div><b>To:</b> {resources.cities.get(cargo.cityIdTo).name}</div>
                <div><b>Order Id:</b> {cargo.orderId}</div>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>

            </div>
        )
    }


}
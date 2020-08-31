import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../../resourceHandler/Resources";
import {AssignedVehicleElement} from "../VehicleAssignment/AssignedVehicleElement";

export class OrderInfo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            order: this.props.order
        }
    }

    render() {
        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        const order = this.state.order;

        const drivers = order.assignedDrivers.map((driver) => (
            <li key={driver.id}>{driver.firstName + ' ' + driver.lastName + ' ' + driver.id}</li>));

        const waypointsTable =
            <table className="table table-sm">
                <thead className="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Cargo</th>
                    <th scope="col">City</th>
                    <th scope="col">Type</th>
                </tr>
                </thead>
                <tbody>
                {
                    order.waypoints.map((w) => (
                                <tr className={w.done ? 'alert alert-success' : ''}>
                                    <td>{w.pathIndex}</td>
                                    <td>{resources.cargos.get(w.cargo).name}</td>
                                    <td>{resources.cities.get(w.cityId).name}</td>
                                    <td>{w.type}</td>
                                </tr>
                            )
                        )
                }
                </tbody>
            </table>


        return (
            <div>
                <h1>Order #{order.id}</h1>
                <div><b>Creation date:</b> {order.creationDate}</div>
                <div><b>Is completed:</b> {order.status}</div>
                <div><b>Vehicle:</b><label id={'assigned-vehicle'}> <AssignedVehicleElement order={order.id} vehicle={order.assignedVehicle}/></label></div>
                <div><b>Drivers:</b></div>
                <ul id={'assigned-drivers'}>
                    {drivers.length === 0 ? 'None' : drivers}
                </ul>
                <div><b>Waypoints:</b>
                <ul>
                    {waypointsTable}
                </ul>
                </div>
                <div id={'further-info'}/>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>
            </div>
        )
    }


}
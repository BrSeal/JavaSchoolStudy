import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../../resourceHandler/Resources";
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";
import {AssignVehicleButton} from "./VehicleAssignment/AssignVehicleButton";
import {AssignDriversButton} from "./AssignDrivers/AssignDriversButton";

export class OrderInfo extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const order = this.props.order;

        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        const drivers =
            <ul>
                {order.assignedDrivers.map((driver) => (
                    <li key={driver.id}>{driver.firstName + ' ' + driver.lastName + ' ' + driver.id}</li>))}
            </ul>

        return (
            <div>
                <h1>Order #{order.id}</h1>
                <div><b>Creation date:</b> {order.creationDate}</div>
                <div><b>Is completed:</b> {order.status}</div>
                <div>
                    <b>Vehicle: </b>{order.assignedVehicle === 0 ? 'None' : vehicleRepository.get(order.assignedVehicle).regNumber}
                </div>
                <div><b>Drivers:</b>
                {order.assignedDrivers.length === 0 ? 'None' : drivers}
                </div>
                <div><b>Waypoints:</b>
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
                </div>
                <div id={'assignVehicle'}>
                    {order.assignedVehicle === 0 ? <AssignVehicleButton id={order.id}/> : ''}
                </div>
                <div id={'assignDrivers'}>
                    {order.assignedVehicle !== 0 && order.assignedDrivers.length === 0 ? <AssignDriversButton/> : ''}
                </div>
                <button className={'btn btn-sm btn-secondary'} onClick={close}>Close</button>
            </div>
        )
    }


}
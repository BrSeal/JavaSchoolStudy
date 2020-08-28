import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../../../../../WebContent/resources/js/src/resourceHandler/Resources";

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
        const vehicle = order.assignedVehicle === 0 ? 'None' : resources.vehicles.get(order.assignedVehicle).regNumber;

        const drivers = order.assignedDrivers.map((driver) => (<li key={driver.id}>{driver.firstName + ' ' + driver.lastName + ' ' + driver.id}</li>));


        const waypoints=order.waypoints;

        //TODO! Make Small table
        const liWaypoints=waypoints.map((w)=>(<li className={w.done?'alert alert-success':''}>{resources.cities.get(w.cityId).name+' '+resources.cargos.get(w.cargo).name+' '+w.type}</li>))

        return (
            <div>
                <h1>Order №{order.id}</h1>
                <label/><b>Creation date:</b> {order.creationDate}<br/>
                <label/><b>Is
                completed:</b> {order.completed ? 'Completed' : order.started ? 'In progress' : 'Assigned'}<br/>
                <label /><b>Vehicle:</b> {vehicle}<br/>
                <label /><b>Drivers:</b><br/>
                <ul>
                {drivers.length===0?'None':drivers}
                </ul>
                <label /><b>Waypoints:</b><br/>
                <ul>
                    {liWaypoints}
                </ul>
                <br/>
                <div id={'further-info'}/>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>
            </div>
        )
    }


}
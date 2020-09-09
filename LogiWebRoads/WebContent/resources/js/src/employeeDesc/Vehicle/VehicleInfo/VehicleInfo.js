import React, {Component} from "react";
import ReactDOM from "react-dom";
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";
import resources from "../../../resourceHandler/Resources";
import {VehicleForm} from "../VehicleForm";

export class VehicleInfo extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const vehicle=this.props.vehicle;

        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        const deleteVehicle = function () {
            if(confirm('You really want to delete vehicle #'+vehicle.id+'?')) {
                vehicleRepository.delete(vehicle.id);
            }
        }

        const showEditForm = function () {
            ReactDOM.render(
                <VehicleForm action={'update'} vehicle={vehicle}/>,document.getElementById('details')
            )
        }

        return (
            <div>
                <h1>Vehicle #{vehicle.id}</h1>
                <div><b>Id:</b> {vehicle.id}</div>
                <div><b>Registration number:</b> {vehicle.regNumber}</div>
                <div><b>Capacity:</b> {vehicle.capacity}</div>
                <div><b>Duty size:</b> {vehicle.dutySize}</div>
                <div><b>Location:</b> {resources.cities.get(vehicle.currentCityId).name}</div>
                <div><b>Current order id:</b> {vehicle.currentOrder===0?'None':vehicle.currentOrder}</div>
                <div><b>Status:</b> {vehicle.ok?'Ok':'Need service'}</div>

                <br/>
                <button className={'btn btn-secondary'} onClick={showEditForm}>Edit</button>
                {vehicle.currentOrder===0?(<button className={'btn btn-secondary'} onClick={deleteVehicle}>Delete</button>):''}
                    <br/>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>

            </div>
        )
    }


}
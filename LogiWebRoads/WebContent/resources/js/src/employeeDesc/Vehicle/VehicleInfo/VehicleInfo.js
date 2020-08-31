import React, {Component} from "react";
import ReactDOM from "react-dom";
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";
import resources from "../../../resourceHandler/Resources";
import {VehicleForm} from "../VehicleForm";

export class VehicleInfo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicle: this.props.vehicle
        }
    }

    render() {
        const vehicle=this.state.vehicle;

        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        const deleteVehicle = function () {
            confirm('You really want to delete vehicle #'+vehicle.id+'?');
            vehicleRepository.delete(vehicle.id);
        }

        const showEditForm = function () {
            ReactDOM.render(
                <VehicleForm action={'update'} vehicle={vehicle}/>,document.getElementById('details')
            )
        }

        return (
            <div>
                <h1>Vehicle #{vehicle.id}</h1>
                <label/><b>Id:</b> {vehicle.id}<br/>
                <label/><b>Registration number:</b> {vehicle.regNumber}<br/>
                <label/><b>Capacity:</b> {vehicle.capacity}<br/>
                <label/><b>Duty size:</b> {vehicle.dutySize}<br/>
                <label/><b>Location:</b> {resources.cities.get(vehicle.currentCityId).name}<br/>
                <label/><b>Current order id:</b> {vehicle.currentOrder===0?'None':vehicle.currentOrder}<br/>
                <label/><b>Status:</b> {vehicle.ok?'Ok':'Need service'}<br/>

                <br/>
                <button className={'btn btn-secondary'} onClick={showEditForm}>Edit</button>
                {vehicle.currentOrder===0&&vehicle.status==='ON_REST'?(<button className={'btn btn-secondary'} onClick={deleteVehicle}>Delete</button>):''}
                    <br/>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>

            </div>
        )
    }


}
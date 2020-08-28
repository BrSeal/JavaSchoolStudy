import React, {Component} from "react";
import ReactDOM from "react-dom";
import driverRepository from "../../../resourceHandler/repositories/DriverRepository";
import DriverForm from "../DriverForm";
import resources from "../../../resourceHandler/Resources";

export class DriverInfo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            driver: this.props.driver
        }
    }

    render() {
        const driver=this.state.driver;

        const close = function () {
            ReactDOM.render('', document.getElementById('details'));
        }

        const deleteDriver = function () {
            driverRepository.delete(driver.id);
        }

        const showEditForm = function () {
            ReactDOM.render(
                <DriverForm action={'update'} driver={driver}/>,document.getElementById('details')
            )
        }

        return (
            <div>
                <h1>Driver info</h1>
                <label/><b>Personal number:</b> {driver.id}<br/>
                <label/><b>First name:</b> {driver.firstName}<br/>
                <label/><b>Last name:</b> {driver.lastName}<br/>
                <label/><b>Location:</b> {resources.cities.get(driver.currentCityId).name}<br/>
                <label/><b>Hours worked:</b> {driver.hoursWorked}<br/>
                <label/><b>Current order id:</b> {driver.currentOrder===0?'None':driver.currentOrder}<br/>
                <label/><b>Status:</b> {driver.status}<br/>

                <br/>
                <button className={'btn btn-secondary'} onClick={showEditForm}>Edit</button>
                {driver.currentOrder===0&&driver.status==='ON_REST'?(<button className={'btn btn-secondary'} onClick={deleteDriver}>Delete</button>):''}
                    <br/>
                <button className={'btn btn-secondary'} onClick={close}>Close</button>

            </div>
        )
    }


}
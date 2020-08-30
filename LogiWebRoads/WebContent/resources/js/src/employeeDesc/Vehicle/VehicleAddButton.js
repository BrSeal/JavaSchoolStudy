import React, {Component} from "react";
import ReactDOM from "react-dom";
import {VehicleForm} from "./VehicleForm";

class VehicleAddButton extends Component {
    render() {
        let vehicle = {
           regNumber:'',
            dutySize:0,
            capacity:0,
            currentCityId:0
        }
        const showForm = function () {
            ReactDOM.render(
                <VehicleForm action="new" vehicle={vehicle}/>,
                document.getElementById('details')
            );
        }
        return (
            <button className='btn btn-sm btn-primary' onClick={showForm}>Add new vehicle</button>
        );
    }
}

export default VehicleAddButton;
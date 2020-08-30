import React, {Component} from "react";
import ReactDOM from "react-dom";
import {VehicleInfo} from "./VehicleInfo";
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";

export class VehicleDetailsButton extends Component{
    constructor(props) {
        super(props);
        this.state={
            id:this.props.vehicleId
        }
        this.showInfo=this.showInfo.bind(this);
    }

    showInfo(){
        ReactDOM.render(<VehicleInfo vehicle={
                vehicleRepository.get(this.state.id)}/>,
            document.getElementById('details'));
    }

    render() {
        return (
            <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>
        );
    }
}
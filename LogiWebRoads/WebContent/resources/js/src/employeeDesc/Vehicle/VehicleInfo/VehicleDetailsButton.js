import React, {Component} from "react";
import ReactDOM from "react-dom";
import {VehicleInfo} from "./VehicleInfo";
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";

export class VehicleDetailsButton extends Component{
    constructor(props) {
        super(props);
        this.showInfo=this.showInfo.bind(this);
    }

    showInfo(){
        let id=this.props.id;
        ReactDOM.render(<VehicleInfo vehicle={
                vehicleRepository.get(id)}/>,
            document.getElementById('details'));
    }

    render() {
        return (
            <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>
        );
    }
}
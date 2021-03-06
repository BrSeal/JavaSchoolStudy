import React, {Component} from "react";
import ReactDOM from "react-dom";
import driverRepository from "../../../resourceHandler/repositories/DriverRepository";
import {DriverInfo} from "./DriverInfo";

export class DriverDetailsButton extends Component {
    constructor(props) {
        super(props);
        this.showInfo = this.showInfo.bind(this);
    }

    showInfo(){
        let id=this.props.id;
        ReactDOM.render(<DriverInfo driver={driverRepository.get(id)}/>,
            document.getElementById('details'));
    }

    render() {
        return (
            <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>
        );
    }
}
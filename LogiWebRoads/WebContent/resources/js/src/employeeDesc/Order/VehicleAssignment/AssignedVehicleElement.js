import React, {Component} from 'react'
import {AssignVehicleForm} from "./AssignVehicleForm";
import ReactDOM from "react-dom";

export class AssignedVehicleElement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            order:this.props.order
        }
        this.showAssignmentForm=this.showAssignmentForm.bind(this);
    }

    showAssignmentForm() {
        let id=this.state.order;
        ReactDOM.render(<AssignVehicleForm order={id}/>,document.getElementById('assigned-vehicle'))
    }

    render() {
        let button=<button className={'btn btn-secondary btn-sm'} onClick={this.showAssignmentForm}>Assign vehicle</button>
        return (this.props.vehicle === 0 ? button:this.props.vehicle);
    }
}
import React, {Component} from 'react'
import ReactDOM from "react-dom";
import {AssignVehicleForm} from "./AssignVehicleForm";

export class AssignVehicleButton extends Component {


    render() {
        let id=this.props.id;
        const showForm= function() {
            ReactDOM.render(<AssignVehicleForm id={id}/>, document.getElementById('assign'))
        }

        return <button className={'btn btn-secondary btn-sm'} onClick={showForm}>Assign vehicle</button>
    }
}
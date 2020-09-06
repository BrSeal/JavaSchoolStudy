import React, {Component} from 'react'
import ReactDOM from "react-dom";
import {AssignDriversForm} from "./AssignDriversForm";
import driverRepository from "../../../../resourceHandler/repositories/DriverRepository";

export class AssignDriversButton extends Component{
    render() {
        let order=this.props.order;
        const showForm= function() {
           let available= driverRepository.getAvailable(order.id)
            if(available.length===0) alert('No available drivers found to order #'+order.id);
            else {
                ReactDOM.render(<AssignDriversForm order={order} available={available}/>, document.getElementById('details'))
            }
        }

        return (
            <button className={'btn btn-secondary btn-sm'} onClick={showForm}>Assign drivers</button>
        );
    }
}
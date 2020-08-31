import React, {Component} from 'react'
import vehicleRepository from "../../../resourceHandler/repositories/VehicleRepository";
import orderRepository from "../../../resourceHandler/repositories/OrderRepositoty";
import {OrderInfo} from "../OrderInfo/OrderInfo";
import ReactDOM from "react-dom";


export class AssignVehicleForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            vehicle: 0
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const order = orderRepository.get(this.props.order);

        orderRepository.assignVehicle(Number(this.state.vehicle), this.props.order);
        ReactDOM.render(<OrderInfo order={order}/>, document.getElementById('details'))
    }

    handleChange(e) {
        this.setState({
            vehicle: e.target.value
        })
        alert(this.state.vehicle);
    }

    render() {
        let availableVehicles=vehicleRepository.getAvailable(this.props.order);
        return (
            <form onSubmit={this.handleSubmit}>
                <select
                    className="form-control"
                    name="assignedVehicle"
                    defaultValue={availableVehicles[0]}
                    onChange={this.handleChange}>
                    {

                        availableVehicles.map((vehicle) => (<option value={vehicle.id}>{vehicle.regNumber}</option>))
                    }
                </select>
                <input className='btn btn-sm btn-secondary' type="submit" value="Assign"/>
            </form>
        );
    }


}
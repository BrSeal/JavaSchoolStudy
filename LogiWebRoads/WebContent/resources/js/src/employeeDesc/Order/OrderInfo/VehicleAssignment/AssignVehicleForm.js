import React, {Component} from 'react'
import vehicleRepository from "../../../../resourceHandler/repositories/VehicleRepository";
import orderRepository from "../../../../resourceHandler/repositories/OrderRepositoty";


export class AssignVehicleForm extends Component {
    constructor(props) {
        super(props);

        let available=vehicleRepository.getAvailable(this.props.id)


        this.state = {
            available: available,
            order: {
                id: this.props.id,
                vehicleId: available[0].id
            }
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        let order=this.state.order;
        order['vehicleId']=Number(order.vehicleId);
        order['id']=Number(order.id);
        orderRepository.assignVehicle(order);
    }

    handleChange(e) {
        let order=this.state.order;
        order.vehicleId=e.target.value;
        this.setState({
            order: order
        })
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <select
                    className="form-control"
                    name="vehicle"
                    defaultValue={this.state.order.vehicleId}
                    onChange={this.handleChange}>
                    {
                        this.state.available.map((vehicle) => (<option value={vehicle.id}>{vehicle.regNumber}</option>))
                    }
                </select>
                <input className='btn btn-sm btn-secondary' type="submit" value="Assign"/>
            </form>
        );
    }


}
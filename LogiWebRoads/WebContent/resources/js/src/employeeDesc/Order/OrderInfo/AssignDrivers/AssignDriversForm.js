import React, {Component} from "react"
import orderRepository from "../../../../resourceHandler/repositories/OrderRepositoty";
import ReactDOM from "react-dom";
import {AssignmentTable} from "./AssignmentTable";
import {AddDriverToOrderForm} from "./AddDriverToOrderForm";
import {OrderInfo} from "../OrderInfo";

export class AssignDriversForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
           drivers:[]
        }
    }

    addDriver = (driverId) => {
        let drivers=this.state.drivers;

        if(drivers.indexOf(driverId)===-1) {
            drivers.push(Number(driverId));
            this.setState({drivers: drivers})
        }
        else {
            alert('Driver #'+driverId+' is already in list!');
        }
    }

    render() {
        let order=this.props.order;
        let drivers=this.state.drivers;

        let saveOrder=()=> {
            let o={
                id:order.id,
                driverIds: drivers
            }
            orderRepository.assignDrivers(o);

           close();
        }

        let close=()=> {
            ReactDOM.render(<OrderInfo order={order}/>, document.getElementById('details'))
        }

        return(
            <div>
                <h1>Driver assignment to order#{order.id}</h1>
                <AssignmentTable drivers={this.state.drivers}/><br/>
                <AddDriverToOrderForm func={this.addDriver} id={order.id} available={this.props.available}/>
                <button className='btn btn-sm btn-secondary' onClick={saveOrder}>Submit</button>
                <button className='btn btn-sm btn-secondary' onClick={close}>Back</button>
            </div>
        )
    }
}
import React, {Component} from "react";
import ReactDOM from "react-dom";
import {DeliveryObjectsTable} from "./DeliveryObjectsTable";
import {DeliveryObjectForm} from "./DeliveryObjectForm";
import orderRepository from "../../../resourceHandler/repositories/OrderRepositoty";

export class OrderForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            order: {
                deliveryObjects: []
            }
        }
    }

    addDeliveryObj = (obj) => {
        let parsedObj = {
            cargo: {
                name: obj.cargoName,
                weight: obj.cargoWeight
            },
            cityIdFrom: obj.from,
            cityIdTo: obj.to
        }

        let o = this.state.order;

        o.deliveryObjects.push(parsedObj);
        this.setState({order: o})
    }

    saveOrder() {
        orderRepository.save(this.state.order);
        ReactDOM.render('', document.getElementById('details'))
    }

    render() {
        return(
            <div>
                <DeliveryObjectsTable objects={this.state.order.deliveryObjects}/><br/>
                <DeliveryObjectForm func={this.addDeliveryObj}/>
                <button className='btn btn-sm btn-secondary' onClick={this.saveOrder}>Submit</button>
            </div>
        )
    }
}





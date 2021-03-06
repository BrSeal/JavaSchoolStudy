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

    render() {
        let o=this.state.order;

        let saveOrder=()=> {
            orderRepository.save(o);

            ReactDOM.render('', document.getElementById('details'));
        }

        let close=()=> {
            ReactDOM.render('', document.getElementById('details'))
        }

        return(
            <div>
                <DeliveryObjectsTable objects={this.state.order.deliveryObjects}/><br/>
                <DeliveryObjectForm func={this.addDeliveryObj}/>
                <button className='btn btn-sm btn-secondary' onClick={saveOrder}>Submit</button>
                <button className='btn btn-sm btn-secondary' onClick={close}>Close</button>
            </div>
        )
    }
}





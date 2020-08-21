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
                deliveryObjects:[]
            }
        }
    }

    addDeliveryObj(obj){
        let o=this.state.order;
        o.deliveryObjects.push(obj);
        this.setState({order:o})
    }

    saveOrder() {
        orderRepository.save(this.state.order);
        ReactDOM.render('',document.getElementById('details'))
    }

    render() {
        ReactDOM.render(
            <div>
                <DeliveryObjectsTable func={this.addDeliveryObj}/><br/>
                <DeliveryObjectForm/>
                <button className='btn btn-sm btn-secondary' onClick={this.saveOrder}>Submit</button>
            </div>,
            document.getElementById('details'));

    }
}





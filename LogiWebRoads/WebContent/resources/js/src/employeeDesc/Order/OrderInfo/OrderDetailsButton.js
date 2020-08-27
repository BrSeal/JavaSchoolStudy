import React, {Component} from "react";
import {OrderInfo} from "./OrderInfo";
import orderRepository from "../../../resourceHandler/repositories/OrderRepositoty";
import ReactDOM from "react-dom";

export class OrderDetailsButton extends Component{
    constructor(props) {
        super(props);
        this.state={
            id:this.props.orderId
        }
        this.showInfo=this.showInfo.bind(this);
    }

    showInfo(){
       ReactDOM.render(<OrderInfo order={
           orderRepository.get(this.state.id)}/>,
           document.getElementById('details'));
    }

    render() {
        return (
           <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>
        );
    }
}
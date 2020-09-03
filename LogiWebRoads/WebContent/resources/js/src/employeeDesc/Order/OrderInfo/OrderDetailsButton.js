import React, {Component} from "react";
import {OrderInfo} from "./OrderInfo";
import orderRepository from "../../../resourceHandler/repositories/OrderRepositoty";
import ReactDOM from "react-dom";

export class OrderDetailsButton extends Component {
    constructor(props) {
        super(props);
        this.showInfo = this.showInfo.bind(this);
    }

    showInfo() {
        let id=this.props.id;
        ReactDOM.render(<OrderInfo order={orderRepository.get(id)}/>,
            document.getElementById('details'));
    }

    render() {
        return <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>;
    }
}
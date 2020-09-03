import React, {Component} from "react";
import {OrderDetailsButton} from "./OrderInfo/OrderDetailsButton";

export class OrderTable extends Component {

    render() {
        let orders=this.props.orders;
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Creation date</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                {
                    Array.from(orders, ([key, value]) => (value))
                        .map((order) => (
                            <tr>
                                <td>{order.id}</td>
                                <td>{order.date}</td>
                                <td>{order.status}</td>
                                <td><OrderDetailsButton id={order.id}/></td>
                            </tr>
                        )
                    )
                }
                </tbody>
            </table>)
    }
}
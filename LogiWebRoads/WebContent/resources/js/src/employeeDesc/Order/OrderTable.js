import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import ReactDOM from "react-dom";

export class OrderTable extends Component {


    render() {
        const showInfo = function(){
            ReactDOM.render('Order details placeholder', document.getElementById('details'));
        }

        let orders = Array.from(resources.orders, ([key, value]) => (value));
        const rows = orders.map((order) => (

            <tr>
                <td>{order.id}</td>
                <td>{order.date}</td>
                <td>{order.completed ? 'Completed' : order.started ? 'In progress' : 'Accepted'}</td>
                <td>
                    <button className={'btn btn-sm btn-secondary'} onClick={showInfo}>Details</button>
                </td>
            </tr>
        ));

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
                {rows}
                </tbody>
            </table>)
    }
}
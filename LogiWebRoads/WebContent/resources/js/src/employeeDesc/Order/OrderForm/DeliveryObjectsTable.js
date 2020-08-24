import React,{Component} from "react";
import resources from "../../../resourceHandler/Resources";

export class DeliveryObjectsTable extends Component {
    render() {
        let deliveryObjects = this.props.objects.map(object =>
            <tr>
                <td>{object.cargo.name}</td>
                <td>{object.cargo.weight}</td>
                <td>{resources.cities.get(+object.cityIdFrom).name}</td>
                <td>{resources.cities.get(+object.cityIdTo).name}</td>
            </tr>
        );

        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Cargo</th>
                    <th scope="col">weight</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                </tr>
                </thead>
                <tbody>
                {deliveryObjects}
                </tbody>
            </table>)
    }
}
import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import {VehicleDetailsButton} from "./VehicleInfo/VehicleDetailsButton";

export class VehicleTable extends Component {
    render() {
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Registration number</th>
                    <th scope="col">State</th>
                    <th scope="col">Location</th>
                    <th scope="col">Current order</th>
                </tr>
                </thead>
                <tbody>
                {
                    Array.from(resources.vehicles, ([key, value]) => (value))
                        .map((v) => (
                            <tr>
                                <td>{v.id}</td>
                                <td>{v.regNumber}</td>
                                <td>{v.ok ? 'Ok' : 'Need service'}</td>
                                <td>{resources.cities.get(v.currentCityId).name}</td>
                                <td>{v.currentOrderId === 0 ? 'None' : v.currentOrderId}</td>
                                <td><VehicleDetailsButton vehicleId={v.id}/></td>
                            </tr>
                        )
                    )
                }
                </tbody>
            </table>)
    }
}

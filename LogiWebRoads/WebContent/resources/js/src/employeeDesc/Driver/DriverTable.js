import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import {DriverDetailsButton} from "./DriverInfo/DriverDetailsButton";

export class DriverTable extends Component {
    render() {
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th scope="col">Location</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                {
                    Array.from(resources.drivers, ([key, value]) => (value))
                        .map((d) => (
                                <tr>
                                    <td>{d.id}</td>
                                    <td>{d.firstName}</td>
                                    <td>{d.lastName}</td>
                                    <td>{resources.cities.get(d.currentCityId).name}</td>
                                    <td>{d.status}</td>
                                    <td><DriverDetailsButton driverId={d.id}/></td>
                                </tr>
                            )
                        )
                }
                </tbody>
            </table>
        )
    }
}

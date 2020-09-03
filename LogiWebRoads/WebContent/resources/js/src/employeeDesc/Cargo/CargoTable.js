import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import {CargoDetailsButton} from "./CargoDetailsButton";

export class CargoTable extends Component {
    render() {
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Weight</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                {
                    Array.from(resources.cargos, ([key, value]) => (value))
                        .map((c) => (
                                <tr>
                                    <td>{c.id}</td>
                                    <td>{c.name}</td>
                                    <td>{c.weight}</td>
                                    <td>{c.status}</td>
                                    <td><CargoDetailsButton id={c.id}/></td>
                                </tr>
                            )
                        )
                }
                </tbody>
            </table>
        )
    }
}

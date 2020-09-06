import React,{Component} from "react";
import resources from "../../../../resourceHandler/Resources";

export class AssignmentTable extends Component {
    render() {
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.drivers.map(driver =>
                    <tr>
                        <td>{resources.drivers.get(driver).id}</td>
                        <td>{resources.drivers.get(driver).firstName}</td>
                        <td>{resources.drivers.get(driver).lastName}</td>
                    </tr>)
                }
                </tbody>
            </table>)
    }
}
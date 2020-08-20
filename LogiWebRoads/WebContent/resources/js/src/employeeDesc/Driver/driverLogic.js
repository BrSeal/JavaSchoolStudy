import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../resourceHandler/Resources";

export function showDrivers() {
    ReactDOM.render(<DriverAddButton/>, document.getElementById('add-button-holder'));
}

class DriverAddButton extends Component {
    render() {
        const showForm = function () {
            alert(resources.cities.get(1).name);
        }
        return (
            <button className='btn btn-sm btn-primary' onClick={showForm}>Add driver</button>
        );
    }
}
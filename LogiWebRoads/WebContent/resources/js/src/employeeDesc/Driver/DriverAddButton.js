import React, {Component} from "react";
import ReactDOM from "react-dom";
import DriverForm from "./DriverForm";

class DriverAddButton extends Component {
    render() {
        let driver = {
            firstName: '',
            lastName: '',
            currentCityId: 1,
            login:'',
            password:''
        }
        const showForm = function () {
            ReactDOM.render(
                <DriverForm action="new" driver={driver}/>,
                document.getElementById('details')
            );
        }
        return (
            <button className='btn btn-sm btn-info' onClick={showForm}>Add driver</button>
        );
    }
}

export default DriverAddButton;
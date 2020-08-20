import React from "react";
import ReactDOM from "react-dom";

export function showVehicles() {
    $.ajax({
        method: "GET",
        url: '../vehicle/',
        success: function (response) {
            ReactDOM.render(<VehiclesTable vehicles={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
            ReactDOM.render(<VehicleAddButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class VehiclesTable extends React.Component {
    render() {
        let vehiclesResult = this.props.vehicles.map(vehicle =>
            <tr>
                <th scope="row">{vehicle.id}</th>
                <td>{vehicle.regNumber}</td>
                <td>{vehicle.ok ? 'OK' : 'Needs service'}</td>
                <td>{vehicle.currentCityName}</td>
                <td>{vehicle.currentOrder === 0 ? 'None' : vehicle.currentOrder}</td>
                <td>
                    <VehicleInfoButton key={vehicle.id} vehicle={vehicle}/>
                </td>
            </tr>
        );
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">regNumber</th>
                    <th scope="col">State</th>
                    <th scope="col">Location</th>
                    <th scope="col">Order</th>
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {vehiclesResult}
                </tbody>
            </table>)
    }
}

class VehicleInfoButton extends React.Component {
    render() {
        let vehicle = this.props.vehicle;
        const showDetails = function () {
            ReactDOM.render(<VehicleDetails vehicle={vehicle}/>,
                document.getElementById('details')
            )
        }
        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={showDetails}>Details</button>
        );
    }
}

class VehicleDetails extends React.Component {
    render() {
        let vehicle = this.props.vehicle;
        return (
            <div>
                <h2>Vehicle № {vehicle.id}</h2>
                <label><b>Registration number: </b>{vehicle.regNumber}</label><br/>
                <label><b>Capacity:</b> {vehicle.capacity}</label><br/>
                <label><b>Crew size:</b> {vehicle.dutySize}</label><br/>
                <label><b>Status:</b> {vehicle.ok ? 'Is ok' : 'Needs service'}</label><br/>
                <label><b>Location:</b> {vehicle.currentCityId}</label><br/>
                <label><b>Current order:</b> {vehicle.currentOrder === 0 ? 'None' : vehicle.currentOrder}
                </label><br/>
                <VehicleUpdateButton vehicle={vehicle}/>
                <VehicleDeleteButton vehicleId={vehicle.id}/>
            </div>
        );
    }
}

class VehicleUpdateButton extends React.Component {
    render() {

        const showForm = () => {
            let vehicle = this.props.vehicle;
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render(<VehicleForm vehicle={vehicle} cities={response} url={'update/'}/>,
                        document.getElementById('details'));
                }
            });
        }
        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={showForm}>Update</button>
        );
    }
}

class VehicleDeleteButton extends React.Component {
    render() {
        let vehicleId = this.props.vehicleId;
        const deleteVehicle = function () {
            $.ajax({
                method: "DELETE",
                url: '../vehicle/delete/' + vehicleId,
                success: function (response) {
                    alert(response);
                    showVehicles()
                },
            });
        }

        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={deleteVehicle}>Delete</button>

        );
    }
}

class VehicleAddButton extends React.Component {
    render() {

        let vehicle = {
            regNumber: '',
            dutySize: 0,
            CurrentCityId: 1,
            currentOrder: 0,
            capacity: 0,
            ok: true
        }
        const showForm = () => {
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render('',
                        document.getElementById('details'));
                    ReactDOM.render(<VehicleForm vehicle={vehicle} cities={response} url={'new/'}/>,
                        document.getElementById('details'));
                }
            });

        }
        return (
            <button className='btn btn-sm btn-primary' onClick={showForm}>Add vehicle</button>
        );
    }
}

class VehicleForm extends React.Component {
    vehicle = this.props.vehicle;

    constructor(props) {
        super(props);
        this.state = {vehicle: this.vehicle};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        const target = e.target;
        let value = target.value;
        const name = target.name;

        let vehicle = this.state.vehicle;
        if (name === 'ok') {
            value = target.checked;
        }
        vehicle[name] = value;
        this.setState({
            vehicle: vehicle
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = this.state.vehicle;
        $.ajax({
            method: 'POST',
            url: '../vehicle/' + this.props.url,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (response) {
                alert(response);
                showVehicles();
            }
        });

    }

    render() {
        let vehicle = this.state.vehicle;

        const options = this.props.cities.map((city) =>
            <option value={city.id}>{city.name}</option>);
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>
                        Registration number:
                    </label>
                    <input className="form-control" name="regNumber" type="text"
                           defaultValue={vehicle.regNumber} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Capacity:
                    </label>
                    <input className="form-control" name="capacity" type="number"
                           defaultValue={vehicle.capacity} onChange={this.handleInputChange} min={100}
                           required/>
                    <br/>
                    <label>
                        Crew size:
                    </label>
                    <input className="form-control" name="dutySize" type="number"
                           defaultValue={vehicle.dutySize} onChange={this.handleInputChange} min={1}
                           required/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select className="form-control" name="currentCityId" defaultValue={vehicle.currentCityId}
                            onChange={this.handleInputChange}>
                        {options}
                    </select>
                    <input
                        name="ok"
                        type="checkbox"
                        checked={vehicle.ok}
                        onChange={this.handleInputChange}/>
                    <label> Is ok</label>
                    <br/>
                    <input  className='btn btn-sm btn-secondary' type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}

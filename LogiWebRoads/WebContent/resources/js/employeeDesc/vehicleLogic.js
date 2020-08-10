const showVehicles = function () {
    $.ajax({
        method: "GET",
        url: '../vehicle/',
        success: function (response) {
            ReactDOM.render(<TableVehicles vehicles={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
            ReactDOM.render(<AddVehicleButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class TableVehicles extends React.Component {
    render() {
        let vehiclesResult = this.props.vehicles.map(vehicle =>
            <tr>
                <th scope="row">{vehicle.id}</th>
                <td>{vehicle.regNumber}</td>
                <td>{vehicle.isOk ? 'OK' : 'Needs service'}</td>
                <td>{vehicle.currentCity.name}</td>
                <td>
                    <InfoVehicleButton key={vehicle.id} vehicle={vehicle}/>
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
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {vehiclesResult}
                </tbody>
            </table>)
    }
}

class InfoVehicleButton extends React.Component {
    render() {
        let vehicle = this.props.vehicle;
        const showDetails = function () {
            ReactDOM.render(<VehicleDetails vehicle={vehicle}/>,
                document.getElementById('details')
            )
        }
        return (
            <button className="detailsButton btn btn-sm btn-secondary"
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
                <label><b>Status:</b> {vehicle.isOk?'Is ok':'Needs service'}</label><br/>
                <label><b>Location:</b> {vehicle.currentCity.name}</label><br/>
                <UpdateVehicleButton vehicle={vehicle}/>
                <DeleteVehicleButton vehicleId={vehicle.id}/>
            </div>
        );
    }
}

class UpdateVehicleButton extends React.Component {

    render() {

        const showForm = () => {
            let vehicle = this.props.vehicle;
            vehicle.currentCity = vehicle.currentCity.id;
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
            <button className="updateButton btn btn-sm btn-secondary"
                    onClick={showForm}>Update</button>
        );
    }
}

class DeleteVehicleButton extends React.Component {
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
                error: function (response) {
                    alert(response);
                }
            });
        }

        return (
            <button className="deleteButton btn btn-sm btn-secondary"
                    onClick={deleteVehicle}>Delete</button>

        );
    }
}

class AddVehicleButton extends React.Component {
    render() {

        let vehicle = {
            regNumber: '',
            dutySize: '',
            CurrentCity: 1,
            capacity: 0,
            isOk: true
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
            <button className='addButton btn btn-sm btn-primary' onClick={showForm}>Add vehicle</button>
        );
    }
}

class VehicleForm extends React.Component {
    vehicle=this.props.vehicle;

    constructor(props) {
        super(props);
        this.state = {vehicle: this.vehicle};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        const target = e.target;
        const name = target.name;
        let value = target.value;

        let vehicle= this.state.vehicle;
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
            data: data,
            headers: {
                "Accept": "application/json; odata=verbose"
            },
            success: function (response) {
                alert("Vehicle #" + response + " was successfully saved");
                showVehicles();
            }
        });

    }
//TODO разобраться с полем checked
    render() {

        const options = this.props.cities.map((city) =>
            <option value={city.id}>{city.name}</option>);
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>
                        Registration number:
                    </label>
                    <input className="form-control" name="regNumber" type="text"
                           defaultValue={this.state.vehicle.regNumber} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Capacity:
                    </label>
                    <input className="form-control" name="capacity" type="number"
                           defaultValue={this.state.vehicle.capacity} onChange={this.handleInputChange} min={100}
                           required/>
                    <br/>
                    <label>
                        Crew size:
                    </label>
                    <input className="form-control" name="dutySize" type="number"
                           defaultValue={this.state.vehicle.dutySize} onChange={this.handleInputChange} min={1}
                           required/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select className="form-control" name="currentCity" defaultValue={this.state.vehicle.currentCity}
                            onChange={this.handleInputChange}>
                        {options}
                    </select>
                    <input
                        name="isOk"
                        type="checkbox"
                        value={this.state.vehicle.isOk}
                        onChange={this.handleInputChange}/>
                        <label> Is ok</label>
                        <br/>
                    <input type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}

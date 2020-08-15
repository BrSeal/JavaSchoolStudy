const showDrivers = function () {
    $.ajax({
        method: "GET",
        url: '../driver/',
        success: function (response) {
            ReactDOM.render(<DriversTable drivers={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
            ReactDOM.render(<DriverAddButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class DriversTable extends React.Component {
    render() {
        let driversResult = this.props.drivers.map(driver =>
            <tr>
                <th scope="row">{driver.id}</th>
                <td>{driver.firstName}</td>
                <td>{driver.lastName}</td>
                <td>{driver.status}</td>
                <td>{driver.currentOrder === 0 ? 'None' : driver.currentOrder}</td>
                <td>
                    <DriverInfoButton key={driver.id} driver={driver}/>
                </td>
            </tr>
        );
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">Status</th>
                    <th scope="col">Current Order</th>
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {driversResult}
                </tbody>
            </table>)
    }
}

class DriverDeleteButton extends React.Component {
    render() {
        let driverId = this.props.driverId;
        const deleteDriver = function () {
            $.ajax({
                method: "DELETE",
                headers: {
                    "Accept": "application/json; odata=verbose"
                },
                url: '../driver/delete/' + driverId,
                success: function (response) {
                    alert(response);
                    showDrivers()
                }
            });
        }

        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={deleteDriver}>Delete</button>

        );
    }
}

class DriverInfoButton extends React.Component {
    render() {
        let drv = this.props.driver;
        const showDetails = function () {
            ReactDOM.render(<DriverDetails driver={drv}/>,
                document.getElementById('details')
            )
        }
        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={showDetails}>Details</button>
        );
    }
}

class DriverUpdateButton extends React.Component {

    render() {
        const showForm = () => {
            let driver = this.props.driver;
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render(<DriverForm driver={driver} cities={response} url={'update/'}/>,
                        document.getElementById('details'));
                }
            });
        }
        return (
            <button className="UpdateDriverButton btn btn-sm btn-secondary"
                    onClick={showForm}>Update</button>
        );
    }
}

class DriverDetails extends React.Component {
    render() {
        let driver = this.props.driver;
        return (
            <div>
                <h2>Driver № {driver.id}</h2>
                <label><b>First name: </b>{driver.firstName}</label><br/>
                <label><b>Last name:</b> {driver.lastName}</label><br/>
                <label><b>Hours worked:</b> {driver.hoursWorked}</label><br/>
                <label><b>Location:</b> {driver.currentCityName}</label><br/>
                <label><b>Status:</b> {driver.status}</label>
                <label><b>Current order:</b> {driver.currentOrder === 0 ? 'None' : driver.currentOrder}</label><br/>
                <DriverUpdateButton driver={driver}/>
                <DriverDeleteButton driverId={driver.id}/>
            </div>
        );
    }
}

class DriverAddButton extends React.Component {
    render() {

        let driver = {
            id:0,
            firstName: '',
            lastName: '',
            currentCityId: 1,
            currentCityName:'',
            currentOrder: 0,
            hoursWorked: 0,
            status: 'ON_REST'
        }
        const showForm = () => {
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render('',
                        document.getElementById('details'));
                    ReactDOM.render(<DriverForm driver={driver} cities={response} url={'new/'}/>,
                        document.getElementById('details'));
                }
            });

        }
        return (
            <button className='btn btn-sm btn-primary' onClick={showForm}>Add driver</button>
        );
    }
}

class DriverForm extends React.Component {
    drv = this.props.driver;

    constructor(props) {
        super(props);
        this.state = {driver: this.drv};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        const target = e.target;
        const name = target.name;
        const value = target.value;

        let drv = this.state.driver;

        drv[name] = value;

        this.setState({
            driver: drv
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = this.state.driver;
        $.ajax({
            method: 'POST',
            url: '../driver/' + this.props.url,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (response) {
                alert(response);
                showDrivers();
            }
        });
    }

    render() {
        let driver = this.state.driver;

        const options = this.props.cities.map((city) =>
            <option value={city.id}>{city.name}</option>);
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>
                        First name:
                    </label>
                    <input className="form-control" name="firstName" type="text"
                           defaultValue={driver.firstName} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Last name:
                    </label>
                    <input className="form-control" name="lastName" type="text"
                           defaultValue={driver.lastName} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Worked hours this month:
                    </label>
                    <input className="form-control" name="hoursWorked" type="number"
                           defaultValue={driver.hoursWorked} onChange={this.handleInputChange} min={0}
                           max={176}/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select className="form-control" name="currentCityId" defaultValue={driver.currentCityId}
                            onChange={this.handleInputChange}>
                        {options}
                    </select>
                    <label>
                        Status:
                    </label>
                    <select className="form-control" name="status" defaultValue={driver.status}
                            onChange={this.handleInputChange}>
                        <option value='ON_REST'>On rest</option>
                        <option value='ON_DUTY_REST'>On duty</option>
                        <option value='ON_DUTY_DRIVING'>Driving</option>
                    </select>
                    <br/>
                    <input className='btn btn-sm btn-secondary' type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}
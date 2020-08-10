const showDrivers = function () {
    $.ajax({
        method: "GET",
        url: '../driver/',
        success: function (response) {
            ReactDOM.render(<TableDrivers drivers={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
            ReactDOM.render(<AddDriverButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class TableDrivers extends React.Component {
    render() {
        let driversResult = this.props.drivers.map(driver =>
            <tr>
                <th scope="row">{driver.id}</th>
                <td>{driver.firstName}</td>
                <td>{driver.lastName}</td>
                <td>{driver.status}</td>
                <td>
                    <InfoDriverButton key={driver.id} driver={driver}/>
                </td>
            </tr>
        );
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">firstName</th>
                    <th scope="col">lastName</th>
                    <th scope="col">Status</th>
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {driversResult}
                </tbody>
            </table>)
    }
}

class DeleteDriverButton extends React.Component{
    render() {
        let driverId=this.props.driverId;
        const deleteDriver = function () {
            $.ajax({
                method: "DELETE",
                url: '../driver/delete/'+driverId,
                success: function (response) {
                    alert(response);
                    showDrivers()
                },
                error: function(response){
                    alert(response);
                }
            });
        }

        return (
            <button className="DeleteDriverButton btn btn-sm btn-secondary"
                    onClick={deleteDriver}>Delete</button>

        );
    }
}

class InfoDriverButton extends React.Component {
    render() {
        let drv = this.props.driver;
        const showDetails = function () {
            ReactDOM.render(<DriverDetails driver={drv}/>,
                document.getElementById('details')
            )
        }
        return (
            <button className="detailsButton btn btn-sm btn-secondary"
                    onClick={showDetails}>Details</button>
        );
    }
}

class UpdateDriverButton extends React.Component {

    render() {

        const showForm = () => {
            let driver = this.props.driver;
            driver.currentCity=driver.currentCity.id;
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
                <label><b>Location:</b> {driver.currentCity.name}</label><br/>
                <label><b>Status:</b> {driver.status}</label>
                <UpdateDriverButton driver={driver}/>
                <DeleteDriverButton driverId={driver.id}/>
            </div>
        );
    }
}

class AddDriverButton extends React.Component {
    render() {

        let driver = {
            firstName: '',
            lastName: '',
            CurrentCity: 1,
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
            <button className='addButton btn btn-sm btn-primary' onClick={showForm}>Add driver</button>
        );
    }
}

class DriverForm extends React.Component {
    drv= this.props.driver;
    constructor(props) {
        super(props);
        this.state = {driver: this.drv};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        const target = e.target;
        const name = target.name;
        const value=target.value;

        let drv = this.state.driver;

            drv[name] = value;

        this.setState({
            driver: drv
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data =this.state.driver;
        $.ajax({
            method: 'POST',
            url: '../driver/'+this.props.url,
            data:data,
            headers: {
                "Accept": "application/json; odata=verbose"
            },
            success: function (response) {
                alert("Driver #" + response + " was successfully saved");
                showDrivers();
            }
        });

    }

    render() {

        const options= this.props.cities.map((city) =>
            <option value={city.id}>{ city.name}</option>);
        return (


            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>
                        First name:
                    </label>
                    <input className="form-control" name="firstName" type="text"
                           defaultValue={this.state.driver.firstName} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Last name:
                    </label>
                    <input className="form-control" name="lastName" type="text"
                           defaultValue={this.state.driver.lastName} onChange={this.handleInputChange} required/>
                    <br/>
                    <label>
                        Worked hours this month:
                    </label>
                    <input className="form-control" name="hoursWorked" type="number"
                           defaultValue={this.state.driver.hoursWorked} onChange={this.handleInputChange} min={0} max={173}/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select  className="form-control" name="currentCity" defaultValue={this.state.driver.currentCity}
                            onChange={this.handleInputChange}>
                        {options}
                    </select>
                    <label>
                        Status:
                    </label>
                    <select className="form-control" name="status" defaultValue={this.state.driver.status}
                            onChange={this.handleInputChange}>
                        <option value='ON_REST'>On rest</option>
                        <option value='ON_DUTY_REST'>On duty</option>
                        <option value='ON_DUTY_DRIVING'>Driving</option>
                    </select>
                    <input type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}
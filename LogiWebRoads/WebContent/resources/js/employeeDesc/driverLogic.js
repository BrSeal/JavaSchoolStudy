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
        let driversLength = this.props.drivers.length;
        let driversResult = this.props.drivers.map(driver =>
            <tr>
                <th scope="row">{driver.id}</th>
                <td>{driver.firstName}</td>
                <td>{driver.lastName}</td>
                <td>{driver.status}</td>
                <td>
                    <InfoButton key={driver.id} driver={driver}/>
                    <UpdateButton key={driver.id + driversLength} driver={driver}/>
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

class InfoButton extends React.Component {
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

class UpdateButton extends React.Component {

    render() {
        let drv = this.props.driver;
        const showForm = () => {
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render(<DriverForm driver={drv} cities={response}/>,
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
            </div>
        );
    }
}

class AddDriverButton extends React.Component {

    render() {

        let driver = {
            firstName: '',
            lastName: '',
            CurrentCity: null,
            hoursWorked: 0,
            status: 'ON_REST'
        }
        const showForm = () => {
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render(<DriverForm driver={driver} cities={response}/>,
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
        const value = name!=='currentCity'?target.value:JSON.parse(target.value);

        let drv = this.state.driver;

            drv[name] = value;

        this.setState({
            driver: drv
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        // $.ajax({
        //     method: "POST",
        //     url: '../driver/new/',
        //     success: function (response) {
        //         alert("Driver #" + response + " was successfully saved")
        //     }
        // });
        let drv = this.state.driver;
        alert(
            drv.firstName + '\n' +
            drv.lastName + '\n' +
            drv.hoursWorked + '\n' +
            drv.currentCity.name + '\n' +
            drv.status + '\n'
        );
    }

    render() {

        const options= this.props.cities.map((city ) =>
            <option value={JSON.stringify(city)}>{ city.name }</option>);
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
                           defaultValue={this.state.driver.hoursWorked} onChange={this.handleInputChange}/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select  className="form-control" name="currentCity" defaultValue={JSON.stringify(this.state.driver.currentCity)}
                            onChange={this.handleInputChange} required>
                        {options}
                    </select>
                    <label>
                        Status:
                    </label>
                    <select className="form-control" name="status" defaultValue={this.state.driver.status}
                            onChange={this.handleInputChange}>
                        <option value='ON_REST'>On rest</option>
                        <option value='ON_DUTY'>On duty</option>
                        <option value='DRIVING'>Driving</option>
                    </select>
                    <input type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}
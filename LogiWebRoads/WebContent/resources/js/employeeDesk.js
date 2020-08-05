const renderContent = function (content) {
    ReactDOM.render(content,
        document.getElementById('content')
    )
}
const renderDetails = function (details) {
    ReactDOM.render(details,
        document.getElementById('details')
    )
}
const renderAddButtonHolder = function (props) {
    ReactDOM.render(props,
        document.getElementById('add-button-holder')
    )
}

const showOrders = function (orders) {
    renderContent('orders');
}

const showVehicles = function (vehicles) {
    renderContent('vehicles');
}

const showDrivers = function () {
    $.ajax({
        method: "GET",
        url: '../driver/',
        success: function (response) {
            renderContent(<TableDrivers drivers={response}/>);
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
                <td>{driver.currentOrder === null ? 'none' : driver.currentOrder.id}</td>
                <td>
                    <InfoButton driver={driver}/>
                </td>
            </tr>
        );
        return (
            <table className="table">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">firstName</th>
                    <th scope="col">lastName</th>
                    <th scope="col">currentOrder</th>
                </tr>
                </thead>
                <tbody>
                {driversResult}
                </tbody>
            </table>)
    }
}

class InfoButton extends React.Component {
    driver=this.props.driver;
    showDetails(){
        renderDetails(<DriverDetails driver={this.driver}/>)
    }

    render() {
        return (
            <button className="detailsButton"
                    onClick={this.showDetails}>Details</button>
        );
    }
}

class DriverDetails extends React.Component {
    render() {
        return (
            <div>
                <h2>Driver № {this.props.driver.id}</h2>
                <label><b>First name: </b>{this.props.driver.firstName}</label><br/>
                <label><b>Last name:</b> {this.props.driver.lastName}</label><br/>
                <label><b>Hours worked:</b> {this.props.driver.hoursWorked}</label><br/>
                <label><b>Location:</b> {this.props.driver.currentCity.name}</label><br/>
                <label><b>Current
                    order:</b> {(this.props.driver.currentOrder === null) ? 'none' : this.props.driver.currentOrder.name}
                </label><br/>
                <label><b>Current
                    vehicle:</b> {(this.props.driver.currentVehicle === null) ? 'none' : this.props.driver.currentVehicle.id}
                </label><br/>

            </div>
        );
    }
}
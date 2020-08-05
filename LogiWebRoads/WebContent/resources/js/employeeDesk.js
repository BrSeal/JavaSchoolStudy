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
                    <InfoButton key={driver.id} driver={driver}/>
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
                    <th scope="col"></th>
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
        let drv=this.props.driver;
       const  showDetails=function(){
            renderDetails(<DriverDetails driver={drv}/>)
        }
        return (
            <button className="detailsButton"
                    onClick={showDetails}>Details</button>
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
                <label><b>Current
                    order:</b> {(driver.currentOrder === null) ? 'none' : driver.currentOrder.name}
                </label><br/>
                <label><b>Current
                    vehicle:</b> {(driver.currentVehicle === null) ? 'none' : driver.currentVehicle.id}
                </label><br/>

            </div>
        );
    }
}
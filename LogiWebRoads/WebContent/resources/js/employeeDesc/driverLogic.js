const renderAddButtonHolder = function (props) {
    ReactDOM.render(props,
        document.getElementById('add-button-holder')
    )
}
const showDrivers = function () {
    $.ajax({
        method: "GET",
        url: '../driver/',
        success: function (response) {
            ReactDOM.render(<TableDrivers drivers={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('detaills'));
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

class AddDriverButton extends React.Component {
    constructor(props) {
        super(props);
        this.showForm = this.showForm.bind(this);
    }

    showForm=function(driver){
        ReactDOM.render(<DriverForm driver={driver}/>, document.getElementById('detaills'));
    }
    render() {
        return (
            <button className='addButton'
                    onClick={this.showForm()}>Details</button>
        );
    }
}

//Проверка на существование поля
//TODO сделать форму для добавления и изменения водителя
// typeof test.friendslist !== 'undefined'
class DriverForm extends React.Component{
    render() {
        return (
            <div>

            </div>
        );
    }

}
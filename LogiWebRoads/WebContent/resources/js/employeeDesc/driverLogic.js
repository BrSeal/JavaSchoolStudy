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
                <td>{driver.currentOrder === null ? 'none' : driver.currentOrder.id}</td>
                <td>
                    <InfoButton key={driver.id} driver={driver}/>
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
            <button className="detailsButton btn btn-sm btn-secondary"
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
    render() {
        const showForm = function (driver) {
            ReactDOM.render(<DriverForm driver={driver}/>, document.getElementById('details'));
        }
        return (
            <button className='addButton btn btn-sm btn-primary'
                    onClick={showForm}>Add driver</button>
        );
    }
}

//Проверка на существование поля
//TODO добавить загрузку списка городов из базы  по запросу формы

class DriverForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName:'',
            lastName:'',
            currentCity:''
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit =this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        const target = e.target;
        const name = target.name;
        let value;
        switch (target.name) {
            case 'firstName':
                value = target.value;
                this.setState({
                    firstName:value
                });
                break;
            case 'lastName':
                value = target.value;
                this.setState({
                    lastName:value
                });
                break;
            case 'select':
                value = target.value;
                this.setState({
                    currentCity:value
                });
        }
    }

    handleSubmit(e){
        e.preventDefault();
        //TODO после первого выбора не работает выбор по выпадающему списку!!
        alert(this.state.firstName+'\n'+this.state.lastName+'\n'+this.state.currentCity);
    }
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                <label>
                    First name:
                </label>
                <input
                    className="form-control"
                    name="firstName"
                    type="text"
                    value={this.state.firstName!=='undefined'?this.state.firstName:''}
                    onChange={this.handleInputChange}/>
                <br/>
                <label>
                   Last name:
                </label>
                <input
                    className="form-control"
                    name="lastName"
                    type="text"
                    value={this.state.lastName!=='undefined'?this.state.lastName:''}
                    onChange={this.handleInputChange}/>
                <br/>
                <label>
                    Location:
                </label>
                <select
                    class="form-control"
                    name="currentCity"
                    value={this.state.currentCity}
                    onChange={this.handleInputChange}>
                    <option value="grapefruit">Грейпфрут</option>
                    <option value="lime">Лайм</option>
                    <option value="coconut">Кокос</option>
                    <option value="mango">Манго</option>
                </select>
                    <input type="submit" value="Save" />
                </div>
            </form>
        );
    }

}
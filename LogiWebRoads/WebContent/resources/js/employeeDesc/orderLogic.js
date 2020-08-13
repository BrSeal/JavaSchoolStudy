const showOrders = function () {
    $.ajax({
        method: "GET",
        url: '../order/',
        success: function (response) {
            ReactDOM.render(<OrdersTable orders={response}/>, document.getElementById('content'));
            ReactDOM.render('', document.getElementById('details'));
            ReactDOM.render(<OrderAddButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class OrderAddButton extends React.Component {

    render() {


        const order = {
            id: 0,
            creationDate: 0,
            waypoints: []
        }
        const showDetails = function () {
            $.ajax({
                method: "GET",
                url: '../city/',
                success: function (response) {
                    ReactDOM.render(<NewOrderTable order={order} cities={response}/>,
                        document.getElementById('content'));
                }
            });
        }

        return (
            <button className='btn btn-sm btn-primary' onClick={showDetails}>Add new order</button>
        );
    }
}

class NewOrderTable extends React.Component {
    cityMap = new Map(this.props.cities.map(city => [city.id, city.name]));
    constructor(props) {
        super(props);
        this.state = {
            waypoints: [],
            waypoint: {
                cargoName: '',
                cargoWeight: 0,
                cargoStatus: "PREPARED",
                amount: 0,
                cityId: 0,
                type: ''
            }
        }
        this.handleChange = this.handleChange.bind(this);
        this.addNextWaypoint = this.addNextWaypoint.bind(this);
    }

    addNextWaypoint() {
        let w = this.state.waypoints;
        let wp = this.state.waypoint;
            w.push(wp);
            this.setState({
                waypoints: w,
                waypoint: {
                    cargoName: '',
                    cargoWeight: 0,
                    cargoStatus: "PREPARED",
                    amount: 0,
                    cityId: 0,
                    cityName: '',
                    type: ''
                }
            });
            this.render();
    }

    handleChange(e) {
        const target = e.target;
        let value = target.value;
        const name = target.name;
        let w = this.state.waypoint;
        w[name] = value;
        this.setState({
            waypoint: w
        });
    }


    render() {
        const cityOptions = this.props.cities.map((city) =>
            <option value={city.id}>{city.name}</option>);

        const saveOrder = function () {
            alert('Save button pressed');
        }

        const waypoints = this.state.waypoints.map(w =>
            <tr>
                <td>{w.cargoName}</td>
                <td>{w.cargoWeight}</td>
                <td>{w.amount}</td>
                <td>{w.cityId}</td>
                <td>{w.type}</td>
            </tr>)

        return (
            <div>
                <table className="table table-striped">
                    <thead className="thead-light">
                    <tr>
                        <th scope="col">Cargo</th>
                        <th scope="col">weight per unit</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Location</th>
                        <th scope="col">Type</th>
                    </tr>
                    </thead>
                    <tbody id={''}>
                    {waypoints}

                    <tr>
                        <td>
                            <input
                                className="form-control"
                                type={'text'}
                                name={'cargoName'}
                                onChange={this.handleChange}
                                required/>
                        </td>
                        <td>
                            <input
                                className="form-control"
                                type={'number'}
                                min={1}
                                name={'cargoWeight'}
                                onChange={this.handleChange}
                                required/>
                        </td>
                        <td>
                            <input
                                className="form-control"
                                type={'number'}
                                name={'amount'}
                                min={1}
                                onChange={this.handleChange}
                                required/>
                        </td>
                        <td>
                            <select className="form-control"
                                    name="cityId"
                                    onChange={this.handleChange} required>
                                {cityOptions}
                            </select>
                        </td>
                        <td>
                            <select className="form-control"
                                    name="type"
                                    onChange={this.handleChange} required>
                                <option value='LOAD'>Load</option>
                                <option value='UNLOAD'>Unload</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <button className='btn btn-sm btn-secondary' onClick={this.addNextWaypoint}>Add waypoint</button>
                <button className='btn btn-sm btn-secondary' onClick={saveOrder}>Save</button>
                <button className='btn btn-sm btn-secondary' onClick={showOrders}>Back to list</button>
            </div>
        );
    }
}

class OrdersTable extends React.Component {
    render() {

        const ordersResult = this.props.orders.map(order => {
            const waypointsRemaining = function () {
                return order.waypoints.reduce((count, waypoint) => {
                    if (waypoint.done === 'false') count++;
                });

            }

            return (
                <tr>
                    <th scope="row">{order.id}</th>
                    <td>{order.creationDate}</td>
                    <td>{order.waypoints.length}</td>
                    <td>{waypointsRemaining()}</td>
                    <td><OrderInfoButton key={order.id} order={order}/></td>
                </tr>
            );
        })
        return (
            <table className="table table-striped">
                <thead className="thead-light">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Creation date</th>
                    <th scope="col">Waypoint count</th>
                    <th scope="col">Waypoint remaining</th>
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {ordersResult}
                </tbody>
            </table>)
    }
}

class OrderInfoButton extends React.Component {
    render() {
        const showDetails = function () {
            ReactDOM.render(<OrderDetails order={this.props.order}/>,
                document.getElementById('details')
            )
        }
        return (
            <button className="btn btn-sm btn-secondary"
                    onClick={showDetails}>Details</button>
        );
    }
}

class OrderDetails extends React.Component {
    render() {
        let order = this.props.order;
        let vehicle;
        let drivers;

        $.ajax({
            method: "GET",
            url: '../vehicle/assigned/' + order.id,
            success: function (response) {
                vehicle = response;
            }
        });
        $.ajax({
            method: "GET",
            url: '../driver/assigned/' + order.id,
            success: function (response) {
                drivers = response;
            }
        });


        const waypoints = function () {
            order.waypoints.map(waypoint => {
                let cargo = waypoint.cargo;
                return (
                    <tr>
                        <td>
                        <span className={'badge badge-' + waypoint.isDone ? 'success' : 'light'}>
                            {waypoint.isDone ? 'Yes' : 'No'}
                        </span>
                        </td>
                        <td>{cargo.name}</td>
                        <td>{cargo.weight}</td>
                        <td>{waypoint.amount}</td>
                        <td>{cargo.weight * waypoint.amount}</td>
                        <td>{waypoint.city}</td>
                        <td>{waypoint.type === 'LOAD' ? 'Load' : 'Unload'}</td>
                        <td><OrderInfoButton key={order.id} order={order}/></td>
                    </tr>
                );
            });
        }

        const driversToUlLi = function () {
            drivers.map(driver => {
                return (
                    <li>{driver.firstName + " " + driver.LastName + " reg. number: " + driver.id}</li>
                )
            });
        }

        const showAssignVehicleForm = function () {
            ReactDOM.render(

            )
        }

        return (
            <div>
                <h1>Order № {order.id}</h1>
                <label><b>Status: </b>{order.isCompleted ? 'Completed' : 'In progress'}</label>
                <label><b>Date of creation: </b>{order.creationDate}</label>
                <label><b>Assigned vehicle: </b>{vehicle.regNumber}</label>
                <label><b>Assigned drivers: </b>{driver.firstName + ' ' + driver.lastName}</label>
                <ul> {driversToUlLi}</ul>
                <table className="table table-striped table-responsive">
                    <thead className="thead-light">
                    <tr>
                        <th scope="col">Done</th>
                        <th scope="col">Cargo</th>
                        <th scope="col">Weight</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Total weight</th>
                        <th scope="col">Location</th>
                        <th scope="col">Type</th>
                        <th scope="col"/>
                    </tr>
                    </thead>
                    <tbody>
                    {waypoints}
                    </tbody>
                </table>
                )
                <AssignVehicleButton action={showAssignVehicleForm} order={order}/><br/>
                <AssignDriverButton/><br/>
                <button id={'backToOrderList'} onClick={showOrders}>Back to list</button>
                <div id={'assignment'}/>
            </div>
        );
    }
}

class AssignDriverButton extends React.Component {


}

/**
 *  Table to array!
 let tdCollection = $("table").find("tr");

 let array = [];
 let temp = {
    DisplayName: "",
    Title: "",
    ID: "",
    Scope: "",
    Description: ""
};
 $.each(tdCollection, function(key, el){
    let i=0;
    let row = $(el).find("td");
    for (let f in temp){
        temp[f] = $(row[i++]).text()
    }
    array.push(temp);
});
 */
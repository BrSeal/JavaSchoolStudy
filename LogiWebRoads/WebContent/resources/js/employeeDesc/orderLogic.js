const showOrders = function () {
    $.ajax({
        method: "GET",
        url: '../order/',
        success: function (response) {
            ReactDOM.render(<OrdersTable orders={response}/>, document.getElementById('content'));
            ReactDOM.render('Orders details placeholder', document.getElementById('details'));
            ReactDOM.render(<OrderAddButton/>, document.getElementById('add-button-holder'));
        }
    })
}

class OrderAddButton extends React.Component {
    render() {
        const showForm = () => {


        }

        return (
            <button className='btn btn-sm btn-primary' onClick={showForm}>Add new order</button>
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
        let vehicle = this.props.vehicle
        let drivers = this.props.drivers

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
                    <li>{driver.firstName+" "+driver.LastName+" reg. number: "+driver.id}</li>
                )
            });
        }
        return (

            <div>
                <h1>Order № {order.id}</h1>
                <label><b>Status: </b>{order.isCompleted ? 'Completed' : 'In progress'}</label>
                <label><b>Date of creation: </b>{order.creationDate}</label>
                <label><b>Assigned vehicle: </b>{vehicle.regNumber}</label>
                <label><b>Assigned drivers: </b>{order.creationDate}</label>
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
            <AssignDriverButton/>
            <AssignVehicleButton/>
            <div id={'assignment'}/>
            </div>


        );
    }
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
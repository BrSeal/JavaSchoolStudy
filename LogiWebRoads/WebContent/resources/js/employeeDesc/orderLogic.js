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

        let order = {

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
                    <td>
                        <InfoOrderButton key={order.id} order={order}/>
                    </td>
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
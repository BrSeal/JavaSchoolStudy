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
                    ReactDOM.render(<OrderForm cities={response}/>,
                        document.getElementById('content'));
                }
            });
        }

        return (
            <button className='btn btn-sm btn-primary' onClick={showDetails}>Add new order</button>
        );
    }
}

class OrderForm extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            cargo:[],
            waypoints: []
        }
    }






}

//input cities
//output waypointFrom, waypointTo, cargo
class WaypointForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cargo: {
                name: '',
                weight: 0,
                status: 'PREPARED'
            },
            waypointFrom: {
                cargo: '',
                cityId: '',
                cityName: '',
                type: 'LOAD'
            },
            waypointTo: {
                cargo: '',
                cityId: '',
                cityName: '',
                type: 'UNLOAD'
            }
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        let from = this.state.waypointFrom;
        let to = this.state.waypointTo;
        let cargo = this.state.cargo;

        let name = e.target.name;
        let value = e.target.value;

        if (name === 'name') cargo.name = value;
        if (name === 'weight') cargo.weight = value;
        if (name === 'from') {
            let c=JSON.parse(value);
            from.cityId = c.id;
            from.cityName = c.name;
        }
        if (name === 'to') {
            let c=JSON.parse(value);
            to.cityId = c.id;
            to.cityName = c.name;
        }


        this.setState({
            cargo: cargo,
            waypointFrom: from,
            waypointTo: to
        })
    }

    handleSubmit(e) {
        let cargo = this.state.cargo;
        let from = this.state.waypointFrom;
        let to = this.state.waypointTo;
        e.preventDefault();
        $.ajax({
            method: 'POST',
            url: '../cargo/new/',
            data: cargo,
            success: function (response) {
                from.cargo = response;
                to.cargo = response;
            }
        });

        this.props.return(from,to,cargo)
    }

    render() {
        const options = this.props.cities.map((city) =>
            <option value={JSON.stringify(city)}>{city.name}</option>);
        return (

            <div>
                <form onSubmit={onSubmit}>
                    <h1>New waypoint</h1>
                    <label><b>Cargo:</b></label>
                    <input className="form-control"
                           type={'text'}
                           name={'name'}
                           onChange={this.handleChange}
                           required/>
                    <label><b>Weight:</b></label>
                    <input className="form-control"
                           type={'number'}
                           name={'weight'}
                           min={1}
                           onChange={this.handleChange}
                           required/>
                    <label><b>From City:</b></label>
                    <select className="form-control"
                            name="from"
                            onChange={this.handleChange}
                            required>
                        {options}
                    </select>
                    <label><b>From City:</b></label>
                    <select className="form-control"
                            name="to"
                            onChange={this.handleChange}
                            required>
                        {options}
                    </select>
                    <input className='btn btn-sm btn-secondary' type={'submit'} value={'Save'}/>
                </form>
            </div>
        );
    }
}
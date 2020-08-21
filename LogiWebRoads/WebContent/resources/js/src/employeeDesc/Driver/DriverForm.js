import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";


class DriverForm extends Component {

    constructor(props) {
        super(props);
        let action = this.props.action;

        let method = action === 'new' ? 'POST' : 'PUT';
        let url = action === 'new' ? 'new/' : 'update/';
        let active=action === 'new';

        this.state = {
            driver: this.props.driver,
            method: method,
            url: url,
            active:active
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        let driver = this.state.driver;
        const target = e.target;
        let value = target.value;
        const name = target.name;


        driver[name] = value;
        this.setState({
            driver: driver
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = this.state.driver;
        let url=this.state.url;
        let method=this.state.method;


        $.ajax({
            method: method,
            url: '../driver/' + url,
            contentType: "application/json",
            data: data,
            success: function (response) {
                alert('Driver was successfully added to database. \n ' +
                    'New driver id='+response);
            },
            error: function (response) {
                alert(response);
            }
        });

    }

    render() {

        const options = resources.cities.forEach((id, city) => <option value={city.id}>{city.name}</option>);

        let updateDriverInputs =
                (<h1>optional inputs</h1>

        )
        ;

        return (
            <form onSubmit={this.handleSubmit}>
                <h1>Driver form</h1>
                <div className="form-group">
                    <label>
                        Registration number:
                    </label>
                    <input
                        className="form-control"
                        name="firstName" type="text"
                        defaultValue={this.state.driver.firstName}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label>
                        Capacity:
                    </label>
                    <input
                        className="form-control"
                        name="lastName"
                        type="text"
                        defaultValue={this.state.driver.firstName}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label>
                        Location:
                    </label>
                    <select
                        className="form-control"
                        name="currentCityId"
                        defaultValue={this.state.driver.currentCityId}
                        onChange={this.handleInputChange}>
                        {options}
                    </select>

                    {this.state.method==="POST"?'':updateDriverInputs}

                    <input className='btn btn-sm btn-secondary' type="submit" value="Save"/>
                </div>
            </form>
        );
    }
}

export default DriverForm;
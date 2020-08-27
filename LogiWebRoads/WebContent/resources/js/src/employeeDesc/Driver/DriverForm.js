import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import driverRepository from "../../resourceHandler/repositories/DriverRepository";


class DriverForm extends Component {

    constructor(props) {
        super(props);

        this.state = {
            driver: this.props.driver,
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

        if(this.props.action==='new') driverRepository.save(data);
        if(this.props.action==='update') driverRepository.update(data);
    }

    render() {
        let cities = Array.from(resources.cities, ([key, value]) => (value));
        const options = cities.map((city) => (<option value={city.id}>{city.name}</option>));

        let updateDriverInputs =
            (
                <div>
                <label/><b>Hours worked:</b>
                <input
                className="form-control"
                name="hoursWorked"
                type="number"
                defaultValue={this.state.driver.hoursWorked}
                onChange={this.handleInputChange}
                min={0}
                max={176}
                required
            />
                </div>)
        ;

        return (
            <form onSubmit={this.handleSubmit}>
                <h1>Driver form</h1>
                <div className="form-group">
                    <label/><b>First name:</b>
                    <input
                        className="form-control"
                        name="firstName"
                        type="text"
                        defaultValue={this.state.driver.firstName}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label/><b>Last name:</b>
                    <input
                        className="form-control"
                        name="lastName"
                        type="text"
                        defaultValue={this.state.driver.lastName}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label/><b>Location:</b>
                    <select
                        className="form-control"
                        name="currentCityId"
                        defaultValue={this.state.driver.currentCityId}
                        onChange={this.handleInputChange}>
                        {options}
                    </select>

                    {this.props.action === "new" ? '' : updateDriverInputs}

                    <input className='btn btn-sm btn-secondary' type="button" onClick={this.handleSubmit} value="Save"/>
                </div>
            </form>
        );
    }
}

export default DriverForm;
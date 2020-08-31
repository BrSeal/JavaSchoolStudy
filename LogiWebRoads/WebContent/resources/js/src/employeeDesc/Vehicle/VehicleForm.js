import React, {Component} from "react";
import resources from "../../resourceHandler/Resources";
import vehicleRepository from "../../resourceHandler/repositories/VehicleRepository";
import ReactDOM from "react-dom";

export class VehicleForm extends Component {

    constructor(props) {
        super(props);

        this.state = {
            vehicle: this.props.vehicle,
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
        this.closeForm = this.closeForm.bind(this)
    }


    closeForm() {
        ReactDOM.render('', document.getElementById('details'))
    }

    handleInputChange(e) {
        let vehicle = this.state.vehicle;
        const target = e.target;
        const value = target.name === 'ok' ? target.checked : target.value;
        const name = target.name;


        vehicle[name] = value;
        this.setState({
            vehicle: vehicle
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = this.state.vehicle;

        if (this.props.action === 'new') vehicleRepository.save(data);
        if (this.props.action === 'update') vehicleRepository.update(data);
    }

    render() {
        let updateVehicleInputs = (
            <div className={'form-check'}>
                <input
                    className="form-check-input"
                    name="ok"
                    type="checkbox"
                    checked={this.state.vehicle.ok}
                    onChange={this.handleInputChange}
                    value={'Is ok'}
                />
                <label>Is ok</label>
            </div>);

        return (
            <form onSubmit={this.handleSubmit}>
                <h1>{this.props.action === 'new' ? 'New vehicle' : 'Edit vehicle #' + this.state.vehicle.id}</h1>
                <div className="form-group">
                    <label/><b>Registration number:</b>
                    <input
                        className="form-control"
                        name="regNumber"
                        type="text"
                        minLength={6}
                        maxLength={6}
                        defaultValue={this.state.vehicle.regNumber}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label/><b>Capacity:</b>
                    <input
                        className="form-control"
                        name="capacity"
                        type="text"
                        minLength={6}
                        maxLength={6}
                        defaultValue={this.state.vehicle.regNumber}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label/><b>Duty size:</b>
                    <input
                        className="form-control"
                        name="dutySize"
                        type="number"
                        min={1}
                        max={3}
                        defaultValue={this.state.vehicle.lastName}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label/><b>Location:</b>
                    <select
                        className="form-control"
                        name="currentCityId"
                        defaultValue={this.state.vehicle.currentCityId}
                        onChange={this.handleInputChange}>
                        {
                            Array
                                .from(resources.cities, ([key, value]) => (value))
                                .map(
                                    (city) => (
                                        <option value={city.id}>{city.name}</option>
                                    )
                                )
                        }
                    </select>

                    {this.props.action === "new" ? '' : updateVehicleInputs}

                    <input className='btn btn-sm btn-secondary' type="button" onClick={this.handleSubmit} value="Save"/>
                    <input className='btn btn-sm btn-secondary' type="button" onClick={this.closeForm} value="Close"/>
                </div>
            </form>
        );
    }
}
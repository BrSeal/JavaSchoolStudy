import React, {Component} from "react";
import resources from "../../../resourceHandler/Resources";

export class DeliveryObjectForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            deliveryObject: {
                cargoName: '',
                cargoWeight: 0,
                from: 1,
                to: 1
            }
        }

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        let deliveryObject = this.state.deliveryObject;
        const target = e.target;
        let value = target.value;
        const name = target.name;


        deliveryObject[name] = value;
        this.setState({
            deliveryObject: deliveryObject
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = {
            cargoName: this.state.deliveryObject.cargoName,
            cargoWeight: Number(this.state.deliveryObject.cargoWeight),
            from: Number(this.state.deliveryObject.from),
            to: Number(this.state.deliveryObject.to)
        };
        this.props.func(data);
    }

    render() {
        let cities = Array.from(resources.cities, ([key, value]) => (value));
        const options = cities.map((city) => (<option value={city.id}>{city.name}</option>));

        return (
            <form onSubmit={this.handleSubmit}>
                <h2>Add delivery</h2>
                <div className="form-group">
                    <label>
                        <b>Cargo name:</b>
                    </label>
                    <input
                        className="form-control"
                        name="cargoName"
                        type="text"
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label>
                        <b>Cargo weight:</b>
                    </label>
                    <input
                        className="form-control"
                        name="cargoWeight"
                        type="number"
                        min={1}
                        onChange={this.handleInputChange}
                        required/>
                    <br/>
                    <label>
                        <b>From:</b>
                    </label>
                    <select
                        className="form-control"
                        name="from"
                        defaultValue={1}
                        onChange={this.handleInputChange}>
                        {options}
                    </select>
                    <label>
                        <b>To:</b>
                    </label>
                    <select
                        className="form-control"
                        name="to"
                        defaultValue={1}
                        onChange={this.handleInputChange}>
                        {options}
                    </select>

                    <input className='btn btn-sm btn-secondary' type="submit" value="Add"/>
                </div>
            </form>
        );
    }
}
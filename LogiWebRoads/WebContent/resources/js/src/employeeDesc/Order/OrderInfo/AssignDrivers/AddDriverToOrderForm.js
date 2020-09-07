import React, {Component} from "react"


export class AddDriverToOrderForm extends Component{
    constructor(props) {
        super(props);
        this.state = {
            driverId:this.props.available[0].id
        }

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleInputChange(e) {
        let value = e.target.value;

        this.setState({
            driverId: value
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        this.props.func(Number(this.state.driverId));
    }

    render() {
        let id=this.state.driverId;
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                        <b>Select driver</b>
                    <select
                        className="form-control"
                        name="driver"
                        defaultValue={id}
                        onChange={this.handleInputChange}>
                        {
                            this.props.available.map((d) => (<option value={d.id}>{d.firstName+' '+d.lastName}</option>))
                        }
                    </select>

                    <input className='btn btn-sm btn-secondary' type="submit" value="Add"/>
                </div>
            </form>
        );
    }
}
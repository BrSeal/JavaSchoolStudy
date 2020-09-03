import React, {Component} from "react";
import ReactDOM from "react-dom";
import {CargoInfo} from "./CargoInfo";
import cargoRepository from "../../resourceHandler/repositories/CargoRepository";

export class CargoDetailsButton extends Component {
    constructor(props) {
        super(props);
        this.showInfo = this.showInfo.bind(this);
    }

    showInfo(){
        let id=this.props.id;
        ReactDOM.render(<CargoInfo cargo={cargoRepository.get(id)}/>,
            document.getElementById('details'));
    }

    render() {
        return (
            <button className={'btn btn-sm btn-secondary'} onClick={this.showInfo}>Details</button>
        );
    }
}
import React, {Component} from "react";
import ReactDOM from "react-dom";
import resources from "../../resourceHandler/Resources";

//TODO!!
class DriverTable extends Component{
    constructor(props) {
        super(props);
        this.state={
            drivers:resources.drivers
        }
    }

    render() {
        return '';
    }

}

import {Component} from "react";

export class DeliveryObjectForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            deliveryObject: {
                cargo: {
                    name: '',
                    weight: 0
                },
                from: 0,
                to: 0
            }
        }
    }

    render() {
        return null;
    }
}
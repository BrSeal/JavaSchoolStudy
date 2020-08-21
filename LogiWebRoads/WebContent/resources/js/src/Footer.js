import React, {Component} from "react";

export class Footer extends Component {

    render() {
        const style = {
            position: "absolute",
            bottom: 0,
            width: "100%",
            backgroundColor: "rgba(199, 199, 199, 0.56)",
            paddingLeft: "15px"
        }

        return (
            <footer style={style} className="footer mt-auto py-3">
                <div className="container-flexible">
                    <span>LogiWeb: Roads</span>
                </div>
            </footer>
        );
    }
}
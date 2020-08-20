import React from "react";
import ReactDOM from "react-dom";
import {showVehicles} from "./employeeDesc/Vehicle/vehicleLogic";
import {showOrders} from "./employeeDesc/Order/orderLogic";
import {showDrivers} from "./employeeDesc/Driver/driverLogic";

export const baseUrl = document.body.dataset.url;


function App() {
    const style = {
        position: "absolute",
        bottom: 0,
        width: "100%",
        backgroundColor: "rgba(199, 199, 199, 0.56)",
        paddingLeft: "15px"
    }

    return (
        <div>
            <nav className="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
                <a className="navbar-brand" href="#">LogiWeb</a>
                <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                        data-target="#navb"
                        aria-expanded="true">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div id="navb" className="navbar-collapse collapse hide">
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Employee Desk</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="{baseUrl}/">Home</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="{baseUrl}/about">About</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div className="container">

                <div id="buttons" className="btn-group row" role="group">
                    <button id="orders-btn" className="btn btn-primary" onClick={showOrders}>Orders</button>
                    <button id="Drivers-btn" className="btn btn-primary" onClick={showDrivers}>Drivers</button>
                    <button id="Vehicles-btn" className="btn btn-primary" onClick={showVehicles}>Vehicles</button>
                </div>
                <div id="add-button-holder" className="row">

                </div>
                <div className="row">
                    <div id="content" className="col-8 p-0">

                    </div>

                    <div id="details" className="col-4">

                    </div>
                </div>
            </div>
            <footer style={style} className="footer mt-auto py-3"
            >
                <div className="container-flexible">
                    <span>LogiWeb: Roads</span>
                </div>
            </footer>
        </div>
    )
}

ReactDOM.render(<App/>, document.getElementById('root'))
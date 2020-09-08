import React, {Component} from "react";
import {baseUrl} from "./App";
import resources from "./resourceHandler/Resources";

export class NavigationBar extends Component{
    render() {
        return (
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
                            <a className="nav-link" href={baseUrl+"/"}>Home</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href={baseUrl+"/about"}>About</a>
                        </li>
                    </ul>
                </div>
                <form className="form-inline mt-2 mt-md-0 pull-right"
                           action={baseUrl+"/logout"}method="post">
                    <input type={'hidden'} name={'_csrf'} value={resources.token}/>
                    <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>

                </form>
            </nav>
        );
    }
}
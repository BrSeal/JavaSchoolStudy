import React from "react";
import ReactDOM from "react-dom";
import {NavigationBar} from "./NavigationBar";
import {MainContainer} from "./MainContainer";
import {Footer} from "./Footer";

export const baseUrl = document.body.dataset.url;


ReactDOM.render(
    <div>
        <NavigationBar/>
        <MainContainer/>
        <Footer/>
    </div>,
    document.getElementById('root'))
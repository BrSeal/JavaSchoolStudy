'use strict';
const renderThis=function(props)
{
    ReactDOM.render(
        <h1>Привет, мир!</h1>,
        document.getElementById('#content')
    );
}
const showOrders=function() {
    renderThis('Orders');
}

const showVehicles=function() {
    renderThis('Vehicles');
}

const showDrivers=function() {
    renderThis('Drivers');
}


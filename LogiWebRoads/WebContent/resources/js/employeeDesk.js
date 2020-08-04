const renderContent = function (content) {
    ReactDOM.render(content,
        document.getElementById('content')
    )
}
const renderDetails = function (details) {
    ReactDOM.render(details,
        document.getElementById('details')
    )
}
const renderAddButtonHolder = function (props) {
    ReactDOM.render(props,
        document.getElementById('add-button-holder')
    )
}



const showOrders = function (orders) {
    renderContent(orders);
}

const showVehicles = function (vehicles) {
    renderContent(vehicles);
}
/**
 * Driver
 * String firstName;
 * String lastName;
 * int hoursWorked;
 * DriverStatus status;
 * Vehicle currentVehicle;
 * City currentCity;
 * Order currentOrder;
 */

const showDrivers = function (drivers) {
    const getDriverInfo=function(){
        $.ajax({
            method: "GET",
            url: '/drivers/',
            success: function(response){
                return response;
            }
        });
        return false;
    }
    const toTable = function (drivers) {
        let driversList = drivers.map((driver) =>
            <tr>
                <td>{driver.id}</td>
                <td>{driver.firstName}</td>
                <td>{driver.lastName}</td>
                <td>{driver.currentOrder.id}</td>
                <button id='showInfo' onClick={renderDetails(getDriverInfo(driver))}>Details</button>
            </tr>
        );
        return (<table>
            <tbody>
            <tr>
                <th>Id</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>currentOrder</th>
            </tr>
            {driversList}
            </tbody>
        </table>)
    }

    renderContent(toTable(drivers));
}

// <h1>Orders</h1>
// <a href="${pageContext.request.contextPath}/order/new">New Order</a>
// <table>
//     <tr>
//     <th>Id</th>
//     <th>Status</th>
//     <th>Action</th>
//     </tr>
//     <c:forEach var="order" items="${orders}">
//         <tr>
//             <td>${order.id}</td>
//             <td>${order.status}</td>
//             <!-- TODO при нажатии на кнопу обновляется инфа справа (orderInfo)-->
//             <td><button id="showOrderInfo" >Details</button></td>
//         </tr>
//     </c:forEach>
// </table>
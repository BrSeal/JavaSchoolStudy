import resources from "../Resources";

class OrderRepository {

    init() {
        let orders = new Map();
        $.ajax({
            method: "GET",
            url: '../order/',
            success: function (response) {
                response.forEach((item) => orders.set(item.id, item));
            }
        });
        return orders;
    }

    save(order) {
        $.ajax({
            method: "POST",
            url: '../order/new/',
            contentType: "application/json",
            data: JSON.stringify(order),
            success: function (response) {
                alert('Order #' + response + ' was successfully saved!');
                resources.updateOrders();
            },
            error: function (response) {
                alert(response);
            }
        });
    }

    assignVehicle(order) {
        $.ajax({
            method: "PUT",
            url: '../order/assignVehicle/',
            contentType: "application/json",
            data: JSON.stringify(order),
            success: function () {
                alert('Vehicle №' + order.vehicleId + ' was successfully assigned to order №!' + order.id);
            },
            error: function (response) {
                alert(response);
            }
        });
    }

    assignDriver(order) {
        $.ajax({
            method: "PUT",
            url: '../order/assignDrivers/',
            contentType: "application/json",
            data: JSON.stringify(order),
            success: function () {
                alert('Drivers were successfully assigned to order №!' + order.id);
            },
            error: function (response) {
                alert(response);
            }
        });
    }

    get(id) {
        $.ajax({
            method: "GET",
            url: '../order/' + id,
            success: function (response) {
                return response;
            },
            error: function (response) {
                alert(response);
            }
        });
    }
}


const orderRepository = new OrderRepository();
export default orderRepository;
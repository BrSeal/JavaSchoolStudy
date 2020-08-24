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
            data: order,
            success: function (response) {
                alert('Order #' + response + ' was successfully saved!')
            },
            error: function (response) {
                alert(response)
            }
        });
    }
}


const orderRepository = new OrderRepository();
export default orderRepository;
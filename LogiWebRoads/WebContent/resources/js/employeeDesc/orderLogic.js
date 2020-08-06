const showOrders = function () {
    $.ajax({
        method: "GET",
        url: '../order/',
        success: function () {
            ReactDOM.render('Orders placeholder', document.getElementById('content'));
            ReactDOM.render('Orders details placeholder', document.getElementById('details'));
            ReactDOM.render('New order button placeholder', document.getElementById('add-button-holder'));
        }
    })
}

// TODO перенести логику из водителя
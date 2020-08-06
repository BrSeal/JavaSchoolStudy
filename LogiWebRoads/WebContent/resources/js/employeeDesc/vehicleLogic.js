const showOrders = function () {
    $.ajax({
        method: "GET",
        url: '../order/',
        success: function () {
            ReactDOM.render('Vehicles placeholder', document.getElementById('content'));
            ReactDOM.render('Vehicles details placeholder', document.getElementById('detaills'));
            ReactDOM.render('New vehicles button placeholder', document.getElementById('add-button-holder'));
        }
    })
}

// TODO перенести логику из водителя
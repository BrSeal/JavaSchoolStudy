class VehicleRepository {
    init() {
        let vehicles = new Map();
        $.ajax({
            method: "GET",
            url: '../vehicle/',
            success: function (response) {
                response.forEach((item) => vehicles.set(item.id, item));
            }
        });
        return vehicles;
    }

    save(vehicle) {
        $.ajax({
            method: 'POST',
            url: '../vehicle/new/',
            contentType: "application/json",
            data: JSON.stringify(vehicle),
            success: function (response) {
                alert('Vehicle was successfully added to database. \n ' +
                    'New vehicle id=' + response);
            },
            error: function (response) {
                alert(response);
            }
        });
    }

    update(vehicle) {
        $.ajax({
            method: 'PUT',
            url: '../vehicle/update/',
            contentType: "application/json",
            data: JSON.stringify(vehicle),
            success: function (response) {
                alert('Vehicle was successfully added to database. \n ' +
                    'New driver id=' + response);
            },
            error: function (response) {
                alert(response);
            }
        });
    }
}

const vehicleRepository = new VehicleRepository();
export default vehicleRepository;
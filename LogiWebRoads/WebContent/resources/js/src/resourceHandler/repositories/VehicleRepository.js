class VehicleRepository {
    init() {
        let vehicles = new Map();
        $.ajax({
            async:true,
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
               alert(response.responseText);
            }
        });
    }

    get(id) {
        let vehicle;
        $.ajax({
            async:false,
            method: "GET",
            url: '../vehicle/get/' + id,
            success: function (response) {
                vehicle = response;
            },
            error: function (response) {
                alert(response.responseText);
            }
        });
        return vehicle;
    }

    getAvailable(id) {
        let vehicles;
        $.ajax({
            async:false,
            method: "GET",
            url: '../vehicle/available/' + id,
            success: function (response) {
                vehicles = response;
            },
            error: function (response) {
                alert(response.responseText);
            }
        });
        return vehicles;
    }

    update(updatedVehicle) {
       let vehicle={
            id:updatedVehicle.id,
            regNumber:updatedVehicle.regNumber,
            dutySize:Number(updatedVehicle.dutySize),
            capacity:Number(updatedVehicle.capacity),
            ok:updatedVehicle.ok,
            currentCityId:Number(updatedVehicle.currentCityId)
        }
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
               alert(response.responseText);
            }
        });
    }

    delete(id){
        $.ajax({
            method: "DELETE",
            url: '../vehicle/delete/' + id,
            success: function () {
                alert('Vehicle #'+id+' was successfully removed from the database!');
            },
            error: function (response) {
               alert(response.responseText);
            }
        });
    }
}

const vehicleRepository = new VehicleRepository();
export default vehicleRepository;
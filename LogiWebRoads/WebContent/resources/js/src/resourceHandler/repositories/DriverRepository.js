import resources from "../Resources";

class DriverRepository {
    init() {
        let drivers = new Map();
        $.ajax({
            async:false,
            method: "GET",
            url: '../driver/',
            success: function (response) {
                response.forEach((item) => drivers.set(item.id, item));
            }
        });
        return drivers;
    }

    save(driver) {
        $.ajax({
            method: 'POST',
            url: '../driver/new/',
            contentType: "application/json",
            data: JSON.stringify(driver),
            success: function (response) {
                resources.updateDrivers();
                alert('Driver was successfully added to database. \n ' +
                    'New driver id=' + response);
            },
            error: function (response) {
                alert(response.responseText);
            }
        });
    }

    update(updatedDrv) {
        let driver={
            id:updatedDrv.id,
            firstName:updatedDrv.firstName,
            lastName:updatedDrv.lastName,
            currentCityId:Number(updatedDrv.currentCityId),
            hoursWorked:Number(updatedDrv.hoursWorked),
            status:updatedDrv.status
        }

        $.ajax({
            method: 'PUT',
            url: '../driver/update/',
            contentType: "application/json",
            data: JSON.stringify(driver),
            success: function () {
                resources.updateDrivers();
                resources.updateOrders();
                alert('Driver #'+updatedDrv.id+' was successfully edited!');
            },
            error: function (response) {
               alert(response.responseText);
            }
        });
    }

    get(id) {
        let driver;
        $.ajax({
            async:false,
            method: "GET",
            url: '../driver/get/' + id,
            success: function (response) {
                driver = response;
            },
            error: function (response) {
               alert(response.responseText);
            }
        });
        return driver;
    }

    delete(id){
        $.ajax({
            method: "DELETE",
            url: '../driver/delete/' + id,
            success: function () {
                resources.updateDrivers()
                alert('Driver #'+id+' was successfully removed from the database!');
            },
            error: function (response) {
               alert(response.responseText);
            }
        });
    }
}

const driverRepository = new DriverRepository();
export default driverRepository;
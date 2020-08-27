class DriverRepository {
    init() {
        let drivers = new Map();
        $.ajax({
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
                alert('Driver was successfully added to database. \n ' +
                    'New driver id=' + response);
            },
            error: function (response) {
                alert(response);
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
            success: function (response) {
                alert('Driver was successfully added to database. \n ' +
                    'New driver id=' + response);
            },
            error: function (response) {
                alert(response);
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
                alert(response);
            }
        });
        return driver;
    }

    delete(id){
        $.ajax({
            async:false,
            method: "DELETE",
            url: '../driver/delete/' + id,
            success: function () {
                alert('Driver №'+id+' was successfully removed from the database!');
            },
            error: function (response) {
                alert(response);
            }
        });
    }
}

const driverRepository = new DriverRepository();
export default driverRepository;
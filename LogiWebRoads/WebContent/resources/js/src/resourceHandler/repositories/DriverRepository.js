class DriverRepository {
        init() {
            let drivers= new Map();
            $.ajax({
                method: "GET",
                url: '../driver/',
                success: function (response) {
                    response.forEach((item) => drivers.set(item.id, item));
                }
            });
            return drivers;
        }

        save(driver){
            $.ajax({
                method: 'POST',
                url: '../driver/new/',
                contentType: "application/json",
                data: JSON.stringify(driver),
                success: function (response) {
                    alert('Driver was successfully added to database. \n ' +
                        'New driver id='+response);
                },
                error: function (response) {
                    alert(response);
                }
            });
        }

    update(driver){
        $.ajax({
            method: 'PUT',
            url: '../driver/update/',
            contentType: "application/json",
            data: JSON.stringify(driver),
            success: function (response) {
                alert('Driver was successfully added to database. \n ' +
                    'New driver id='+response);
            },
            error: function (response) {
                alert(response);
            }
        });
    }
}

const driverRepository =new DriverRepository();
export default driverRepository;
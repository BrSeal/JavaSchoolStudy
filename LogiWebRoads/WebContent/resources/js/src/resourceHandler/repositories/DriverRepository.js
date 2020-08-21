class DriverRepository {
        init() {
            let drivers= new Map();
            $.ajax({
                method: "GET",
                url: '../driver/',
                success: function (response) {
                    response.forEach((item, index) => drivers.set(item.id, item));
                }
            });
            return drivers;
        }
}

const driverRepository =new DriverRepository();
export default driverRepository;
import orderRepository from "./repositories/OrderRepositoty";
import cityRepository from "./repositories/CityRepository";
import driverRepository from "./repositories/DriverRepository";
import vehicleRepository from "./repositories/VehicleRepository";
import cargoRepository from "./repositories/CargoRepository";

class ResourceService {
    constructor() {
        let token;
        $(function () {
            token = $("meta[name='_csrf']").attr("content");
                let header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
        this.token=token;
        this.drivers = driverRepository.init();
        this.orders = orderRepository.init();
        this.vehicles = vehicleRepository.init();
        this.cargos = cargoRepository.init();
        this.cities = cityRepository.init();
    }

    updateOrders(){
        this.orders = orderRepository.init();
    }

    updateDrivers(){
        this.drivers = driverRepository.init();
    }

    updateVehicles(){
        this.vehicles = vehicleRepository.init();
    }

    updateCargos(){
        this.cargos = cargoRepository.init();
    }
}

const resources = new ResourceService();
export default resources;
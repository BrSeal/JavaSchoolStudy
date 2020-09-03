class CargoRepository {
    init() {
        let cargos = new Map();
        $.ajax({
            async:false,
            method: "GET",
            url: '../cargo/',
            success: function (response) {
                response.forEach((item) => cargos.set(item.id, item));
            }
        });
        return cargos;
    }

    get(id) {
        let cargo;
        $.ajax({
            async:false,
            method: "GET",
            url: '../cargo/get/'+id,
            success: function (response) {
                cargo=response;
            }
        });
        return cargo;
    }
}

const cargoRepository = new CargoRepository();
export default cargoRepository;
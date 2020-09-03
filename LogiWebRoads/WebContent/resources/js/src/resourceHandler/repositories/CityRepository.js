class CityRepository {
    init() {
        let cities = new Map();
        $.ajax({
            async:false,
            method: "GET",
            url: '../city/',
            success: function (response) {
                response.forEach((item) => cities.set(item.id, item));
            }
        });
        return cities;
    }
}

const cityRepository = new CityRepository();
export default cityRepository;
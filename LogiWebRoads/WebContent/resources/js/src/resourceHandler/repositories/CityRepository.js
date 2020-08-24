class CityRepository {
    init() {
        let cities = new Map();
        $.get("../city/")
            .done(function (response) {
                response.forEach((item) => cities.set(item.id, item));
            })
            .fail(function (response) {
                console.log(response);
            })
        return cities;
    }

    getCity(id){
        $.get("../city/"+id)
            .done(function (response) {
                return  response;
            })
            .fail(function (response) {
                console.log(response);
            })
    }
}

const cityRepository=new CityRepository();
export default cityRepository;
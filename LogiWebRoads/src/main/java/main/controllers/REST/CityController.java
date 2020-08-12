package main.controllers.REST;

import main.model.logistic.City;
import main.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<City> getCities() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable int id) {
        return service.get(id);
    }

}


package main.controllers.REST;

import main.model.logistic.City;
import main.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseBody
    public List<City> getCities() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public City getCityById(@PathVariable int id) {
        return service.get(id);
    }

}


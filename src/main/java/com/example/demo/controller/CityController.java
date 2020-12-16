package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.cityService.CityService;
import com.example.demo.service.countrySerice.ICoutryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {
    @Autowired
    ICoutryService coutryService;

    @Autowired
    CityService cityService;

    @ModelAttribute("country")
    public Iterable<Country> countries(){
        return coutryService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView getCreate() {
        ModelAndView modelAndView = new ModelAndView("bigcityboy/create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView postCreate(@ModelAttribute City city){
        City city1 = city;
        cityService.save(city1);
        ModelAndView modelAndView = new ModelAndView("bigcityboy/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "Thêm thành phố mới thành công !!!");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView getList(@RequestParam(value = "name",required = false,defaultValue = "") String name) {
        ModelAndView modelAndView = new ModelAndView("bigcityboy/list");
        Iterable<City> cities = cityService.findAllByNameContaining(name);
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView getUpdate(@PathVariable Long id) {
        City city = cityService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("bigcityboy/update");
        modelAndView.addObject("city",city);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView postUpdate(@ModelAttribute City city) {
        cityService.save(city);
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("bigcityboy/list");
        modelAndView.addObject("city",city);
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        City city = cityService.findById(id).get();
        cityService.delete(city.getId());
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("bigcityboy/list");
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getProvince(@PathVariable Long id){
        return new ResponseEntity(cityService.findById(id).get(), HttpStatus.OK);
    }
}

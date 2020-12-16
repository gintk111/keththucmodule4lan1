package com.example.demo.service.cityService;

import com.example.demo.model.City;
import com.example.demo.service.Iservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends Iservice<City> {
    Iterable<City> findAllByNameContaining(String name);
}

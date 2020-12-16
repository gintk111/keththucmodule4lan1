package com.example.demo.repository;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country,Long> {
}

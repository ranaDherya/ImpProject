package com.example.demo.service;

import com.example.demo.dto.CountryDTO; 

import java.util.List;

public interface CountryService {
List<CountryDTO> findAll();
List<CountryDTO> findById(Integer id);
CountryDTO updateById(Integer id, CountryDTO countryDTO);
void deleteById(Integer id);
}

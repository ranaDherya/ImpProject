package com.example.demo.service;

import com.example.demo.dto.CityDTO; 

import java.util.List;

public interface CityService {
List<CityDTO> findAll();
List<CityDTO> findById(Integer id);
CityDTO updateById(Integer id, CityDTO cityDTO);
void deleteById(Integer id);
}

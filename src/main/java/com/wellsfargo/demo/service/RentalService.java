package com.example.demo.service;

import com.example.demo.dto.RentalDTO; 

import java.util.List;

public interface RentalService {
List<RentalDTO> findAll();
List<RentalDTO> findById(Integer id);
RentalDTO updateById(Integer id, RentalDTO rentalDTO);
void deleteById(Integer id);
}
